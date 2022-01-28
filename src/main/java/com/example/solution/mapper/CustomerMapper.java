package com.example.solution.mapper;

import com.example.solution.api.model.Customer;
import com.example.solution.persistence.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerEntity customerToCustomerEntity(Customer customer);

    Customer customerEntityToCustomer(CustomerEntity customerEntity);
}
