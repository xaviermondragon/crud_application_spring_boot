package com.example.solution.service;

import com.example.solution.api.model.Customer;
import com.example.solution.errorhandling.ApiException;
import com.example.solution.mapper.CustomerMapper;
import com.example.solution.persistence.CustomerEntity;
import com.example.solution.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Long saveCustomer(Customer customer) {
        CustomerEntity customerEntity = CustomerMapper.INSTANCE.customerToCustomerEntity(customer);
        return customerRepository.save(customerEntity).getId();
    }

    public Customer getCustomer(Long id) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);

        if (customerEntityOptional.isPresent()) {
            CustomerEntity customerEntity = customerEntityOptional.get();
            Customer customer = CustomerMapper.INSTANCE.customerEntityToCustomer(customerEntity);
            return customer;
        }

        throw new ApiException(HttpStatus.NOT_FOUND, "Entity not found in DB with id " + id + ".");
    }

    public Customer updateCustomer(Long id, Map<Object, Object> fields) {
        // If the customer doesn't already exist in the database an exception will be thrown
        Customer customer = getCustomer(id);

        CustomerEntity customerEntity = CustomerMapper.INSTANCE.customerToCustomerEntity(customer);
        customerEntity.setId(id);

        fields.forEach((k, v) -> {
            // use reflection to get field k on customer and set it to value v
            Field field = ReflectionUtils.findField(CustomerEntity.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, customerEntity, v);
        });

        customerRepository.save(customerEntity);
        return CustomerMapper.INSTANCE.customerEntityToCustomer(customerEntity);
    }

    public void deleteCustomerById(Long id) {
        // If the customer doesn't already exist in the database an exception will be thrown
        Customer customer = getCustomer(id);
        customerRepository.deleteById(id);
    }


}
