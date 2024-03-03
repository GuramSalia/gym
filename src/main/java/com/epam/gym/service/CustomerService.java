package com.epam.gym.service;

import com.epam.gym.model.Customer;
import com.epam.gym.model.Trainer;
import com.epam.gym.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> create(Customer customer) {
        return Optional.of(customerRepository.save(customer));
    }

    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }
}
