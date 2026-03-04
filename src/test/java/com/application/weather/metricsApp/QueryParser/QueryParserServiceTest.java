package com.application.weather.metricsApp.QueryParser;

import com.application.weather.metricsApp.DatabaseConfig;
import dev.langchain4j.model.chat.ChatModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class QueryParserServiceTest {

    @Mock
    private ChatModel chatModel;

    @Mock
    private DatabaseConfig databaseConfig;

    @InjectMocks
    private QueryParserService queryParserService;

    @Test
    public void givenAValidSQLQueryWhenParseThenConnectToDB(){

        when(chatModel.chat(anyString())).thenReturn("SELECT FROM ");
        when(databaseConfig.runSelectStatement(anyString())).thenReturn(List.of());

        queryParserService.parseQuery("Sample query");

        verify(databaseConfig, times(1)).runSelectStatement(anyString());

    }

    @Test
    public void givenAnInValidSQLQueryWhenParseThenNotConnectToDB(){

        when(chatModel.chat(anyString())).thenReturn("Not a valid prompt");
        when(databaseConfig.runSelectStatement(anyString())).thenReturn(List.of());

        queryParserService.parseQuery("Sample query");

        verify(databaseConfig, never()).runSelectStatement(anyString());

    }

    @Test
    public void givenABlankStringWhenParseThenNotConnectToDB(){

        when(chatModel.chat(anyString())).thenReturn(" ");
        when(databaseConfig.runSelectStatement(anyString())).thenReturn(List.of());

        queryParserService.parseQuery("Sample query");

        verify(databaseConfig, never()).runSelectStatement(anyString());

    }
  
}