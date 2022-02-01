package com.example.solution.api;

import com.example.solution.api.model.Customer;
import com.example.solution.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/hello-world")
    public String helloWorld() {return "Hello World!";}

    @PostMapping("/customer")
    public ResponseEntity<Long> saveCustomer(@RequestBody @Valid Customer customer)  {
        var response = customerService.saveCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") Long id) {
            var response= customerService.getCustomer(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        var response = customerService.getAllCustomers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/customer-by-first-name")
    public ResponseEntity<List<Customer>> getCustomersByFirstName(@RequestParam(name = "first_name") String firstName) {
        var response = customerService.getCustomersByFirstName(firstName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(path = "/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
       var response = customerService.updateCustomer(id, fields);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomerById(@PathVariable(value = "id") Long id) {
        customerService.deleteCustomerById(id);
        return String.format("Customer with id %d was deleted.", id);
    }
}
