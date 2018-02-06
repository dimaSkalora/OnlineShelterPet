package com.online.shelter.pet.servlet;

import com.online.shelter.pet.servlet.model.Role;
import com.online.shelter.pet.servlet.model.User;
import com.online.shelter.pet.servlet.to.PetWithDownplayWeight;
import com.online.shelter.pet.servlet.web.pet.PetRestController;
import com.online.shelter.pet.servlet.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
            System.out.println();

            PetRestController petController = appCtx.getBean(PetRestController.class);
            List<PetWithDownplayWeight> filteredMealsWithExceeded =
                    petController.getBetween(
                            LocalDate.of(2016, Month.MAY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2018, Month.MAY, 31), LocalTime.of(11, 0));
            filteredMealsWithExceeded.forEach(System.out::println);
        }
    }
}
