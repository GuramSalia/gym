package com.epam.gym;

import com.epam.gym.model.Customer;
import com.epam.gym.model.Trainer;
import com.epam.gym.model.Training;
import com.epam.gym.model.TrainingType;
import com.epam.gym.service.CustomerService;
import com.epam.gym.service.TrainerService;
import com.epam.gym.service.TrainingService;
import com.epam.gym.service.TrainingTypeService;
import com.epam.gym.utils.BeanProvider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static java.util.Calendar.*;

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
        TrainingTypeService trainingTypeService = BeanProvider.getTrainingTypeService();

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

        Customer customer_3 = new Customer();
        customer_3.setFirstName("Dave");
        customer_3.setLastName("Miller");
        Calendar cal = Calendar.getInstance();
        cal.set(YEAR, 1990);
        cal.set(MONTH, JULY);
        cal.set(DATE, 5);
        customer_3.setDob(cal.getTime());
        customer_3.setAddress("123 Main St");
        customer_3.setIsActive(true);
        customer_3.setPassword("123456");
        customer_3.setUsername("Dave.Miller");
        customer_3.setTrainers(Set.of(optionalTrainer1.get(), optionalTrainer2.get()));

        Optional<Customer> customer3SavedOptional = customerService.create(customer_3);
        customer3SavedOptional.ifPresent(customer -> log.info(customer.getFirstName() + " saved"));

        log.info("customer_3 found by id of 5 :" + customerService.findById(5).toString());

        Customer customer_4 = new Customer();
        customer_4.setFirstName("Maria");
        customer_4.setLastName("Li");
        customer_4.setDobEasy(1980, 0, 25);
        customer_4.setAddress("55123 Main St");
        customer_4.setIsActive(true);
        Optional<Customer> customer4SavedOptional = customerService.create(customer_4);
        customer4SavedOptional.ifPresent(customer -> log.info(customer.toString()));

        log.info("update customer_3:");
        Customer customer_3Saved = customer3SavedOptional.get();
        customer_3Saved.setFirstName("John");
        customer_3Saved.setLastName("Doe");
        customer_3Saved.setPassword("1122");
        customer3SavedOptional = customerService.update(customer_3Saved, customer_3Saved.getUsername(), "123456");
        customer3SavedOptional.ifPresent(customer -> log.info("Updated customer_3: " + customer.toString()));

        log.info("getByUsername customer_3 :");
        customer3SavedOptional = customerService.getByUsername("John.Doe1", "1122");
        customer3SavedOptional.ifPresent(customer -> log.info(customer.toString()));

        log.info("updatePassword customer_3 :");
        customer_3 = customer3SavedOptional.get();
        customer3SavedOptional = customerService.updatePassword(customer_3, customer_3.getUsername(), "1122", "2233");

        log.info("activateActiveCustomer customer_3: ");
        boolean activatedActive = customerService.activateCustomer(
                customer3SavedOptional.get(),
                customer3SavedOptional.get().getUsername(),
                "2233");
        log.info("activateActiveCustomer customer_3: " + activatedActive);

        log.info("deactivateActiveCustomer customer_3:");
        boolean deactivatedActive = customerService.deactivateCustomer(
                customer3SavedOptional.get(),
                customer3SavedOptional.get().getUsername(),
                "2233");
        log.info("deactivateActiveCustomer customer_3: " + deactivatedActive);

        log.info("deactivateInactiveCustomer customer_3:");
        boolean deactivatedInactive = customerService.deactivateCustomer(
                customer3SavedOptional.get(),
                customer3SavedOptional.get().getUsername(),
                "2233");
        log.info("deactivateInactiveCustome customer_3: " + deactivatedInactive);


        log.info("activateInactiveCustomer customer_3:");
        boolean activatedInactive = customerService.activateCustomer(
                customer3SavedOptional.get(),
                customer3SavedOptional.get().getUsername(),
                "2233");
        log.info("deactivateInactiveCustomer customer_3: " + activatedInactive);



        log.info("delete customer_1:");
        Customer customer = new Customer();
        customer.setFirstName("Lucy");
        customer.setLastName("Rodriguez");
        customer.setDobEasy(1980, 0, 25);
        customer.setAddress("55123 Main St");
        customer.setIsActive(true);
        customer.setPassword("111");
        customerService.create(customer);
        log.info("Created customer with id " + customer.getUserId());
        customerService.delete(customer.getUsername(), "111");

        // ----------------------------------------------------------------

        log.info("create trainer");
        log.info("update trainer");
        log.info("updated trainer password");
        log.info("activate active trainer");
        log.info("deactivate active trainer");
        log.info("deactivate inactive trainer");
        log.info("activate inactive trainer");


        log.info("create training with trainer of the same training type");
        log.info("create training with trainer of the different training type");

        // ----------------------------------------------------------------

        TrainingType PILATES = trainingTypeService.getTrainingType(TrainingType.TrainingTypeEnum.PILATES);
        TrainingType STRENGTH = trainingTypeService.getTrainingType(TrainingType.TrainingTypeEnum.STRENGTH);
        TrainingType GROUP = trainingTypeService.getTrainingType(TrainingType.TrainingTypeEnum.GROUP);
        TrainingType CARDIO = trainingTypeService.getTrainingType(TrainingType.TrainingTypeEnum.CARDIO);
        TrainingType HIIT = trainingTypeService.getTrainingType(TrainingType.TrainingTypeEnum.HIIT);
        TrainingType PERSONAL = trainingTypeService.getTrainingType(TrainingType.TrainingTypeEnum.PERSONAL);
        TrainingType YOGA = trainingTypeService.getTrainingType(TrainingType.TrainingTypeEnum.YOGA);

        log.info("create trainer");
        Trainer trainer_3 = new Trainer();
        trainer_3.setFirstName("Olivia");
        trainer_3.setLastName("Bruno");
        trainer_3.setPassword("123");
        trainer_3.setIsActive(true);
        trainer_3.setSpecialization(PILATES);
        trainerService.create(trainer_3);


//        log.info("update trainer");
//        log.info("updated trainer password");
//        log.info("activate active trainer");
//        log.info("deactivate active trainer");
//        log.info("deactivate inactive trainer");
//        log.info("activate inactive trainer");
//
//
//        log.info("create training with trainer of the same training type");
//        log.info("create training with trainer of the different training type");


        log.info("\n\n>>>> END  ==============\n");
    }
}
