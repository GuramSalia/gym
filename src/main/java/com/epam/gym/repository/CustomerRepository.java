package com.epam.gym.repository;

import com.epam.gym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByUsername(String username);

    Optional<Customer> findByUsernameAndPassword(String username, String password);
}
