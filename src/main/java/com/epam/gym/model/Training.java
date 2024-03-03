package com.epam.gym.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Slf4j
@Entity
@Table(name = "TRAININGS")
public class Training {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "TRAINING_ID")
    private int trainingId;

    @Column(name = "CUSTOMER_ID")
    private int customerId;

    @Column(name = "TRAINER_ID")
    private int trainerId;

    @Column(name = "TRAINING_NAME")
    private String trainingName;

    // switch (trainingTypeId with type AND I SHOULD GET IT FROM TYPE TABLE)
    // private TrainingType trainingType;
    @Column(name = "TRAINING_TYPE_ID")
    private int TrainingTypeID;

    @Column(name = "TRAINING_DATE")
    private Date trainingDate;

    @Column(name = "TRAINING_DURATION")
    private int trainingDurationInMinutes;

    @Override
    public String toString() {return "Training{" + "trainingId=" + trainingId + ", trainingName='" + trainingName + '\'' + '}';}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return getTrainingId() == training.getTrainingId() && Objects.equals(getTrainingName(), training.getTrainingName());
    }

    @Override
    public int hashCode() {return Objects.hash(getTrainingId(), getTrainingName());}
}
