package com.example.solution.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiError {

    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    @JsonProperty("request_id")
    private String requestId;
}
