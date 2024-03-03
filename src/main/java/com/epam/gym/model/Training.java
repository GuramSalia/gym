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
@Table(name = "Trainings")
public class Training {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int trainingId;
    private int trainerId;
    private int customerId;
    private String trainingName;

    // switch (trainingTypeId with type)
    // private TrainingType trainingType;
    private int TrainingTypeID;
    private Date date;
    private int durationInMinutes;

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
