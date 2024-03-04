package com.epam.gym.utils;

import com.epam.gym.model.User;
import com.epam.gym.repository.CustomerRepository;
import com.epam.gym.repository.TrainerRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Component
@Getter
@Setter
public class UsernameGenerator {
    private final CustomerRepository customerRepository;
    private final TrainerRepository trainerRepository;

    public UsernameGenerator(CustomerRepository customerRepository, TrainerRepository trainerRepository) {
        this.customerRepository = customerRepository;
        this.trainerRepository = trainerRepository;
    }

    public String generateUsername(User user) {
                String base = user.getFirstName() + "." + user.getLastName();
                int counter = 1;
                String username = base;
                Function<String, Boolean> isUniqueInCustomers = u -> isUnique(user, u, customerRepository.findAll());
                Function<String, Boolean> isUniqueInTrainers = u -> isUnique(user, u, trainerRepository.findAll());

                while (!isUniqueInCustomers.apply(username) || !isUniqueInTrainers.apply(username)) {
                    username = base + counter++;
                }

                log.info(">>>> Generating username");
                return username;

//        return "abcdefghijkl";
    }

    private <T extends User> boolean isUnique(User targetUser, String username, List<T> users) {
        return users.stream().noneMatch(user -> user.getUsername().equals(username) && !user.equals(targetUser));
    }
}
