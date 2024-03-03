package com.epam.gym.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Slf4j
@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private int customerId;

    @Column(name = "DATE_OF_BIRTH")
    private Date dob;

    @Column(name = "ADDRESS")
    private String address;

    @Override
    public String toString() {return "Customer{" + "customerId=" + customerId + '}';}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getCustomerId() == customer.getCustomerId();
    }

    @Override
    public int hashCode() {return Objects.hash(getCustomerId(), getDob(), getAddress());}
}
