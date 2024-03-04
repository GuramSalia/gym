package com.epam.gym.service;

import com.epam.gym.model.Trainer;
import com.epam.gym.model.Training;
import com.epam.gym.model.TrainingType;
import com.epam.gym.repository.TrainerRepository;
import com.epam.gym.repository.TrainingRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainerRepository trainerRepository;

    public TrainingService(TrainingRepository trainingRepository, TrainerRepository trainerRepository) {
        this.trainingRepository = trainingRepository;
        this.trainerRepository = trainerRepository;
    }

    public Optional<Training> findById(int id) {
        return trainingRepository.findById(id);
    }

    @Transactional
    public Optional<Training> create(Training training) {

        TrainingType trainingType = training.getTrainingType();
        TrainingType trainerSpecialization;
        Optional<Trainer> optionalTrainer = Optional.ofNullable(training.getTrainer());

        if (optionalTrainer.isEmpty()) {
            log.info("There is no such trainer as indicated by training");
            trainerSpecialization = null;
        } else {

            Optional<Trainer> trainerFromDb = trainerRepository.findByUsername(optionalTrainer.get().getUsername());
            if (trainerFromDb.isEmpty()) {
                log.error("Cannot create training, because the trainer does not exist");
                return Optional.empty();
            }

            trainerSpecialization = optionalTrainer.get().getSpecialization();
        }

        if (areMismatchingTrainingTypes(trainingType, trainerSpecialization)) {
            log.error("cannot create training, because the trainer has a different specialization");
            return Optional.empty();
        }

        try {
            trainingRepository.save(training);
            log.info(">>>> Creating training: " + training.getTrainingName());
            return trainingRepository.findById(training.getTrainingId());
        } catch (Exception e) {
            log.error("something went wrong", e);
            return Optional.empty();
        }
    }

    private boolean areTrainingTypesMatching(TrainingType type1, TrainingType type2) {
        boolean matching = false;
        if (type1 != null && type2 != null) {
            matching = type1.equals(type2);
        } else if (type1 == null && type2 == null) {
            matching = true;
        }
        return matching;
    }

    private boolean areMismatchingTrainingTypes(TrainingType type1, TrainingType type2) {
        return !areTrainingTypesMatching(type1, type2);
    }
}
