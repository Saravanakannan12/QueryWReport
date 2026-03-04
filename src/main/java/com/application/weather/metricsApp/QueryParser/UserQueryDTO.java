package com.application.weather.metricsApp.QueryParser;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserQueryDTO {

    @NotBlank(message = "Please enter a valid query")
    private String userQuery;
}
