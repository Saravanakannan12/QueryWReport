package com.application.weather.metricsApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RequiredArgsConstructor
@Slf4j
@Data
@RestController
@RequestMapping("/v1")
public class QueryParserController {

    private final QueryParserService queryParserService;

    @GetMapping("/userQuery")
    public void parseUserQuery(@RequestBody @NotBlank(message = "Please enter a valid query") String userQuery) throws SQLException {
        log.info("User query {}", userQuery);
        queryParserService.parseQuery(userQuery);

    }

}
