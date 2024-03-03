package com.epam.gym;

import com.epam.gym.model.Trainer;
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

		Optional<Trainer> trainer1Optional = trainerService.findById(1);
		Optional<Trainer> trainer2Optional = trainerService.findById(2);

        trainer1Optional.ifPresent(trainer -> log.info(trainer.toString()));
		trainer2Optional.ifPresent(trainer -> log.info(trainer.toString()));

		//		inMemoryStorage.getCustomers().values().stream().forEach(x -> log.info(x.toString()));
		//		log.info("^^^^ TRAINEES\n");
		//		inMemoryStorage.getTrainers().values().stream().forEach(x -> log.info(x.toString()));
		//		log.info("^^^^ TRAINERS\n");
		//		inMemoryStorage.getTrainings().values().stream().forEach(x -> log.info(x.toString()));
		//		log.info("^^^^ TRAININGS\n");
		//
		//		log.info("\n\tSTART\n");
		//		log.info("list of TRAINEES:");
		//		inMemoryStorage.getCustomers().values().stream().forEach(x -> log.info(x.toString()));
		//		log.info("list of TRAINERS:");
		//		inMemoryStorage.getTrainers().values().stream().forEach(x -> log.info(x.toString()));
		//		log.info("list of TRAININGS:");
		//		inMemoryStorage.getTrainings().values().stream().forEach(x -> log.info(x.toString()));

		log.info("\n\n>>>> END  ==============\n");


    }


}
