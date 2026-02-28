package com.application.weather.metricsApp;

import com.application.weather.metricsApp.Metric.QueryResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
@RequiredArgsConstructor
public class QueryParserService {

    private final ChatModel chatModel;

    private final ObjectMapper objectMapper;

    private static final String SYSTEM_PROMPT = """
                <|begin_of_text|><|start_header_id|>system<|end_header_id|>You are an expert in extracting Natural language. Clearly watch for multiple values in this query.
                #Instructions:
                   - You need to extract the below mentioned four fields from the query
                   - If you could not find any of those values, return as null for that value
                   - You should look for statistical_operations could be average, min, max, sum
                   - You should look for metrics and it could be something related to weather sensor values namely temperature, wind speed, humudity, rain fall etc.,
                   - You should look carefully the time_range as it could be in english words(Eg. last 2 weeks, previous week) and numbers as well.
                Extract the following fields from the user query:
                - statistical_operations
                - metrics
                - sensor_ids
                - time_range
            
                Return only JSON.
                <|eot_id>
                <|start_header_id|>user<|end_header_id|>Here is the user query: {{userQuery}}<|eot_id|>
                user"
            """;

    public QueryResult parseQuery(String userQuery) throws JsonProcessingException {

        PromptTemplate promptTemplate = PromptTemplate.from(SYSTEM_PROMPT);
        Prompt promptToLLMModel = promptTemplate.apply(Map.of("userQuery", userQuery));
        String formattedJson = chatModel.chat(promptToLLMModel.text());

        return objectMapper.readValue(formattedJson, QueryResult.class);
    }
}
