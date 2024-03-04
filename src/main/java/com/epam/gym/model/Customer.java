package com.epam.gym.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static java.time.LocalDate.now;

@Getter
@Setter
@Slf4j
@Entity
@Table(name = "CUSTOMERS")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Customer extends User {
    //    @Id
    //    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //    @Column(name = "CUSTOMER_ID")
    //    private Integer customerId;

    @Column(name = "DATE_OF_BIRTH")
    private Date dob;

    @Column(name = "ADDRESS")
    private String address;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = true)
    @Column(name = "USER_ID")
    private Integer userId;

    @ManyToMany
    @JoinTable(name = "CUSTOMERS_TRAINERS", joinColumns = {@JoinColumn(name = "CUSTOMER_ID")}, inverseJoinColumns = {@JoinColumn(name = "TRAINER_ID")})
    Set<Trainer> trainers = new HashSet<>();

    public void setDobEasy(Integer year, Integer month, Integer day) {
        if (year > now().getYear()) {
            log.info("birth year cannot be in the future");
            return;
        }

        if (month > 11 || month < 0) {
            log.info("birth month cannot be in the future");
            return;
        }

        if (day > 31 || day < 0) {
            log.info("birth day cannot be in the future");
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        setDob(cal.getTime());
    }

    @Override
    public String toString() {return "Customer{" + "customerId=" + userId + " " + getFirstName() + " " + getLastName() + '}';}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return userId == customer.getUserId();
    }

    @Override
    public int hashCode() {return Objects.hash(getUserId(), getDob(), getAddress());}
}
