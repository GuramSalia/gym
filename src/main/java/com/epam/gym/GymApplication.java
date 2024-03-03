package com.epam.gym;

import com.epam.gym.model.Customer;
import com.epam.gym.model.Trainer;
import com.epam.gym.model.Training;
import com.epam.gym.service.CustomerService;
import com.epam.gym.service.TrainerService;
import com.epam.gym.service.TrainingService;
import com.epam.gym.utils.BeanProvider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("com.epam.gym.repository")
@EntityScan("com.epam.gym.model")
@Slf4j
public class GymApplication {

    public static void main(String[] args) {

		SpringApplication.run(GymApplication.class, args);

		TrainerService trainerService = BeanProvider.getTrainerService();
		CustomerService customerService = BeanProvider.getCustomerService();
		TrainingService trainingService = BeanProvider.getTrainingService();

		log.info("\n\n>>>> START  ==============\n");

		Optional<Trainer> optionalTrainer1 = trainerService.findById(1);
		Optional<Trainer> optionalTrainer2 = trainerService.findById(2);
		Optional<Training> optionalTraining1 = trainingService.findById(1);
		Optional<Training> optionalTraining2 = trainingService.findById(2);
		Optional<Customer> optionalCustomer1 = customerService.findById(3);
		Optional<Customer> optionalCustomer2 = customerService.findById(4);

		optionalTrainer1.ifPresent(trainer -> log.info(trainer.toString()));
		optionalTrainer2.ifPresent(trainer -> log.info(trainer.toString()));
		optionalTraining1.ifPresent(training -> log.info(training.toString()));
		optionalTraining2.ifPresent(training -> log.info(training.toString()));
		optionalCustomer1.ifPresent(training -> log.info(training.toString()));
		optionalCustomer2.ifPresent(training -> log.info(training.toString()));

		log.info("\n\n>>>> END  ==============\n");


    }


}
