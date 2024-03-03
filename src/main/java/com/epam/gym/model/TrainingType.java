package com.epam.gym.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TRAINING_TYPES")
public class TrainingType {

    public enum TrainingTypeEnum {
        CARDIO, STRENGTH, HIIT, YOGA, PILATES, GROUP, PERSONAL,
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "TRAINING_TYPE_ID")
    private int trainingTypeId;


    @Enumerated(EnumType.STRING)
    @Column(name = "TRAINING_TYPE_NAME")
    private TrainingTypeEnum trainingType;

    @Override
    public String toString() {
        return "TrainingType{" + "trainingType=" + trainingType + '}';
    }
}
