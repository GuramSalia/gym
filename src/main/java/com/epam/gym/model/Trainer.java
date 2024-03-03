package com.epam.gym.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Getter
@Setter
@Slf4j
@Entity
@Table(name = "TRAINERS")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAINER_ID")
    private int trainerId;

    //    @Column(name = "TRAINING_TYPE_ID")
    //    private int trainingTypeID;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "TRAINING_TYPE_ID", referencedColumnName = "TRAINING_TYPE_ID", nullable = true)
    @Enumerated(EnumType.ORDINAL)
    private TrainingType specialization;

    // need to delete later
    @Column(name = "USER_ID")
    private int userId;

    @Override
    public String toString() {
        return "Trainer{" + "trainerId=" + trainerId + ", specialization=" + specialization + ", userId=" + userId + '}';
    }

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
