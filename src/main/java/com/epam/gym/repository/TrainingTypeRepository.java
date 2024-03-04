package com.epam.gym.repository;

import com.epam.gym.model.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Integer> {
    TrainingType findByTrainingType(TrainingType.TrainingTypeEnum trainingType);
}
