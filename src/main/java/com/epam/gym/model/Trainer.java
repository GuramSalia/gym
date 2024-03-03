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
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Trainer extends User{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "TRAINER_ID")
//    private Integer trainerId;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "TRAINING_TYPE_ID", referencedColumnName = "TRAINING_TYPE_ID", nullable = true)
    @Enumerated(EnumType.ORDINAL)
    private TrainingType specialization;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = true)
    @Column(name = "USER_ID")
    private Integer userId;

    @Override
    public String toString() {
        return "Trainer{" + "trainerId=" + userId + " " + getFirstName() + " " + getLastName() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return userId == trainer.getUserId();
    }

    @Override
    public int hashCode() {return Objects.hash(getUserId());}
}
