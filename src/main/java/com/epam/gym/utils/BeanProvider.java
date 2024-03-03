package com.epam.gym.utils;

import com.epam.gym.config.ApplicationContextProvider;
import com.epam.gym.service.CustomerService;
import com.epam.gym.service.TrainerService;
import com.epam.gym.service.TrainingService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

public class BeanProvider {
    private static final ApplicationContext context = ApplicationContextProvider.getContext();

    public static TrainerService getTrainerService() {
        return context.getBean(TrainerService.class);
    }

    public static CustomerService getCustomerService() {
        return context.getBean(CustomerService.class);
    }

    public static TrainingService getTrainingService() {
        return context.getBean(TrainingService.class);
    }
}
