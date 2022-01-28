package com.example.solution.persistence;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "first_name", nullable = false, columnDefinition = "varchar(128)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "varchar(128)")
    private String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "varchar(128)")
    private String email;

    @Column(name = "age", columnDefinition = "int")
    private int age;

    @Column(name = "address", columnDefinition = "varchar(256)")
    private String address;
}
