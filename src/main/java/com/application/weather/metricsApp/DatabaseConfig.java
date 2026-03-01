package com.application.weather.metricsApp;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DatabaseConfig {

    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> runSelectStatement(String sqlQuery) {

        String query = sqlQuery.trim().toUpperCase();
        if (!(query.startsWith("SELECT"))) {
            throw new IllegalArgumentException("Only SELECT statements are allowed.");
        }

        return jdbcTemplate.queryForList(sqlQuery);
    }

}
