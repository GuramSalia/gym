package com.epam.gym.service;

import com.epam.gym.model.Trainer;
import com.epam.gym.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Optional<Trainer> create(Trainer trainer) {
        return Optional.of(trainerRepository.save(trainer));
    }

    public Optional<Trainer> findById(int id) {
        return trainerRepository.findById(id);
    }
}
