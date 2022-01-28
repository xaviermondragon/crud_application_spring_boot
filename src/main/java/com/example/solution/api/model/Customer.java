package com.example.solution.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Customer {

    @NotBlank
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters long.")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 characters long.")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 99, message = "Age should not be greater than 99")
    private int age;

    @Size(max = 255)
    private String address;
}
