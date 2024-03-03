package com.epam.gym.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Getter
@Setter
@Slf4j
public class Trainer {
    private int trainerId;
    private int trainingTypeID;
    //    private TrainingType specialization;
    // need to delete later
    private int userId;

    @Override
    public String toString() {return "Trainer{" + "trainerId=" + trainerId + '}';}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return getTrainerId() == trainer.getTrainerId();
    }

    @Override
    public int hashCode() {return Objects.hash(getTrainerId());}
}
