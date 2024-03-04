package com.epam.gym.service;

import com.epam.gym.model.TrainingType;
import com.epam.gym.repository.TrainingTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainingTypeService {
    private final TrainingTypeRepository trainingTypeRepository;

    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository) {this.trainingTypeRepository = trainingTypeRepository;}

    public TrainingType getTrainingType(TrainingType.TrainingTypeEnum trainingType) {

        return trainingTypeRepository.findByTrainingType(trainingType);
    }
}
