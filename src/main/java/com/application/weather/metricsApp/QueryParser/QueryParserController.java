package com.application.weather.metricsApp.QueryParser;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Slf4j
@Data
@RestController
@RequestMapping("/v1")
public class QueryParserController {

    private final QueryParserService queryParserService;

    @GetMapping("/userQuery")
    public ResponseEntity<List<Map<String, Object>>> parseUserQuery(@RequestBody @NotBlank(message = "Please enter a valid query") String userQuery) {
        log.info("User query {}", userQuery);
        return ResponseEntity.ok(queryParserService.parseQuery(userQuery));
    }

}
