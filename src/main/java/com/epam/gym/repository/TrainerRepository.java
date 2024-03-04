package com.epam.gym.repository;

import com.epam.gym.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
        Optional<Trainer> findByUsername(String username);
        Optional<Trainer> findByUsernameAndPassword(String username, String password);
}
