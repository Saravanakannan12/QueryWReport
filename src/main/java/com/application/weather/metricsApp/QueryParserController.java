package com.application.weather.metricsApp;

import com.application.weather.metricsApp.Metric.QueryResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@Slf4j
@Data
@RestController
@RequestMapping("/v1")
public class QueryParserController {

    //private String userQuery = "Give me the average temperature and humidity for sensor 1 in the last day";

    private final QueryParserService queryParserService;

    @GetMapping("/userQuery")
    public void parseUserQuery(@RequestBody @NotBlank(message = "Please enter a valid query") String userQuery) throws JsonProcessingException {
        log.info("User query {}", userQuery);
        QueryResult queryResult = queryParserService.parseQuery(userQuery);
        log.info("Query after LLM modified {}", queryResult.toString());
    }

}
