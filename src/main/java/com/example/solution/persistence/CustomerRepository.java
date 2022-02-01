package com.example.solution.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    List<CustomerEntity> findByFirstNameOrderByLastNameAsc(String firstName);
}
