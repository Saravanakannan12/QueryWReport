package com.application.weather.metricsApp.QueryParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class QueryParserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QueryParserService queryParserService;

    @InjectMocks
    private QueryParserController queryParserController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(queryParserController).build();
    }

    @Test
    public void givenAValidUserQueryWhenParseQueryThenReturnIsOk() throws Exception {

        List<Map<String, Object>> response = List.of(Map.of("key", "value"));
        when(queryParserService.parseQuery(anyString())).thenReturn(response);

        UserQueryDTO userQueryDTO = new UserQueryDTO();
        userQueryDTO.setUserQuery("Sample query");

        mockMvc.perform(get("/v1/userQuery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userQueryDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenABlankUserQueryWhenParseThenReturnIsBadRequest() throws Exception {

        String blankQuery = " ";
        mockMvc.perform(get("/v1/userQuery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(blankQuery)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAEmptyUserQueryWhenParseThenReturnIsBadRequest() throws Exception {
        String emptyQuery = "";
        mockMvc.perform(get("/v1/userQuery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emptyQuery)))
                .andExpect(status().isBadRequest());
    }

}