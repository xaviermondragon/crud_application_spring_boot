package com.example.solution.api;

import com.example.solution.api.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.Null;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @LocalServerPort
    private int port;


    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = this.port;
        RestAssured.basePath = "/api/v1/";
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Juan José");
        customer.setLastName("Arreola");
        customer.setEmail("j_jose_arreola@mymail.com");
        customer.setAge(99);
        customer.setAddress("Confabulario 1.");

        return customer;
    }

    @Test
    void helloWorldShouldSucceed() {
        RestAssured
                .given().when().request(Method.GET, "hello-world")
                .then().assertThat().statusCode(HttpStatus.OK.value());
    }

    @Test
    void saveCustomerShouldSucceed() throws JsonProcessingException {
        Customer customer = createCustomer();
        var saveResponse  = RestAssured
                .given().contentType("application/json").body(new ObjectMapper().writeValueAsString(customer))
                .when().request(Method.POST, "customer")
                .then().assertThat().statusCode(HttpStatus.CREATED.value())
                .extract().as(Long.class);
        assertEquals(1L, saveResponse);

        var getResponse = RestAssured
                .given().pathParam("id", saveResponse)
                .when().request(Method.GET, "customer/{id}")
                .then().assertThat().statusCode(HttpStatus.OK.value())
                .extract().as(Customer.class);

        assertEquals(customer.getFirstName(), getResponse.getFirstName());
        assertEquals(customer.getLastName(), getResponse.getLastName());
        assertEquals(customer.getEmail(), getResponse.getEmail());
        assertEquals(customer.getAge(), getResponse.getAge());
        assertEquals(customer.getAddress(), getResponse.getAddress());
    }

    @Test
    void saveCustomerShouldFailOnMissingLastName() throws JsonProcessingException {
        Customer customer = createCustomer();
        customer.setFirstName(null);
        RestAssured
                .given().contentType("application/json").body(new ObjectMapper().writeValueAsString(customer))
                .when().request(Method.POST, "customer")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
    }
}