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
        customer.setAge(83);
        customer.setAddress("Confabulario 3.");

        return customer;
    }

    @Test
    void helloWorldShouldSucceed() {
        RestAssured
                .given().when().request(Method.GET, "hello-world")
                .then().assertThat().statusCode(HttpStatus.OK.value());
    }

    @Test
    void getCustomerShouldSucceed() {
     RestAssured
                .given().pathParam("id", 1)
                .when().request(Method.GET, "customer/{id}")
                .then().assertThat().statusCode(HttpStatus.OK.value());
    }

    @Test
    void getCustomerShouldFailOnNonExistentCustomer() {
        RestAssured
                .given().pathParam("id", 100)
                .when().request(Method.GET, "customer/{id}")
                .then().assertThat().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void getAllCustomersShouldSucceed() {
        RestAssured
                .given()
                .when().request(Method.GET, "customer")
                .then().assertThat().statusCode(HttpStatus.OK.value());
    }

    @Test
    void saveCustomerShouldSucceed() throws JsonProcessingException {
        Customer customer = createCustomer();
       RestAssured
                .given().contentType("application/json").body(new ObjectMapper().writeValueAsString(customer))
                .when().request(Method.POST, "customer")
                .then().assertThat().statusCode(HttpStatus.CREATED.value())
                .extract().as(Long.class);
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