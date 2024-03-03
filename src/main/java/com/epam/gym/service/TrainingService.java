package com.epam.gym.service;

import com.epam.gym.model.Training;
import com.epam.gym.repository.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {this.trainingRepository = trainingRepository;}

    public Optional<Training> create(Training training) {
        return Optional.of(trainingRepository.save(training));
    }

    public Optional<Training> findById(int id) {
        return trainingRepository.findById(id);}
}
