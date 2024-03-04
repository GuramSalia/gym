package com.epam.gym.service;

import com.epam.gym.model.Customer;
import com.epam.gym.model.Trainer;
import com.epam.gym.repository.TrainerRepository;
import com.epam.gym.utils.RandomPasswordGenerator;
import com.epam.gym.utils.UsernameGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final UsernameGenerator usernameGenerator;

    public TrainerService(TrainerRepository trainerRepository, UsernameGenerator usernameGenerator) {
        this.trainerRepository = trainerRepository;
        this.usernameGenerator = usernameGenerator;
    }

    public Optional<Trainer> findById(int id) {
        return trainerRepository.findById(id);
    }

    @Transactional
    public Optional<Trainer> create(Trainer trainer) {

        try {

            trainer.setUsername(usernameGenerator.generateUsername(trainer));
            if (trainer.getPassword() == null) {
                trainer.setPassword(RandomPasswordGenerator.generateRandomPassword());
            }
            log.info(">>>> Creating trainer with username: " + trainer.getUsername());

            if (UserService.isInvalidUser(trainer)) {
                log.error("invalid customer");
                return Optional.empty();
            }

            return Optional.of(trainerRepository.save(trainer));
        } catch (Exception e) {
            log.error("error creating customer", e);
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<Trainer> update(Trainer trainer, String username, String password) {

        if (isInvalidUsernamePassword(trainer, username, password)) {
            log.error("invalid username or password");
            return Optional.empty();
        }

        if (UserService.isInvalidUser(trainer)) {
            log.info("invalid user");
            return Optional.empty();
        }

        try {
            trainer.setUsername(usernameGenerator.generateUsername(trainer));
            log.info(">>>> Updating trainer with username: " + trainer.getUsername());
            return Optional.of(trainerRepository.save(trainer));
        } catch (Exception e) {
            log.error("error updating trainer", e);
            return Optional.empty();
        }
    }

    public Optional<Trainer> getByUsername(String username, String password) {

        Optional<Trainer> trainerOptional = trainerRepository.findByUsername(username);
        if (trainerOptional.isEmpty()) {
            log.error("invalid username or password");
            return Optional.empty();
        }

        Trainer trainer = trainerOptional.get();
        if (isInvalidUsernamePassword(trainer, username, trainer.getPassword())) {
            log.error("invalid username or password");
            return Optional.empty();
        }

        log.info(">>>> Getting trainer using getByUsername: " + username);
        return trainerOptional;
    }




    @Transactional
    public Optional<Trainer> updatePassword(Trainer trainer, String username, String currentPassword, String newPassword) {

        if (isInvalidUsernamePassword(trainer, username, currentPassword)) {
            log.error("invalid username or password");
            return Optional.empty();
        }

        if (UserService.isInvalidUser(trainer)) {
            return Optional.empty();
        }

        try {
            trainer.setPassword(newPassword);
            return Optional.of(trainerRepository.save(trainer));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean activateTrainer(Trainer trainer, String username, String password) {

        if (isInvalidUsernamePassword(trainer, username, password)) {
            log.error("invalid username or password");
            return false;
        }

        if (trainer.getIsActive()) {
            log.info("trainer is already active");
            return false;
        }

        try {
            trainer.setIsActive(true);
            trainerRepository.save(trainer);
            log.info("trainer is active");
            return true;
        } catch (Exception e) {
            log.error("couldn't activate the trainer", e);
            return false;
        }
    }

    @Transactional
    public boolean deactivateTrainer(Trainer trainer, String username, String password) {

        if (isInvalidUsernamePassword(trainer, username, password)) {
            log.error("invalid username or password");
            return false;
        }

        if (!trainer.getIsActive()) {
            log.info("trainer is already deactivated");
            return false;
        }

        try {
            trainer.setIsActive(false);
            trainerRepository.save(trainer);
            log.info("trainer is deactivated");
            return true;
        } catch (Exception e) {
            log.error("couldn't deactivate the trainer");
            return false;
        }
    }

    private boolean isValidUsernamePassword(Trainer trainer, String username, String password) {
        Trainer trainerInDb;
        try {
            Optional<Trainer> trainerOptional = trainerRepository.findByUsernameAndPassword(username, password);
            if (trainerOptional.isEmpty()) {
                log.error("Could not find the trainer");
                return false;
            }

            trainerInDb = trainerOptional.get();
        } catch (Exception e) {
            log.error("something went wrong", e);
            return false;
        }
        return trainerInDb.equals(trainer);
    }

    private boolean isInvalidUsernamePassword(Trainer trainer, String username, String password) {
        return !isValidUsernamePassword(trainer, username, password);
    }
}
