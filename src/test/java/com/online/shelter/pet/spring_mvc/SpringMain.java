package com.online.shelter.pet.spring_mvc;

import com.online.shelter.pet.spring_mvc.Profiles;
import com.online.shelter.pet.spring_mvc.model.Role;
import com.online.shelter.pet.spring_mvc.model.User;
import com.online.shelter.pet.spring_mvc.to.PetWithDownplayWeight;
import com.online.shelter.pet.spring_mvc.web.pet.PetRestController;
import com.online.shelter.pet.spring_mvc.web.user.AdminRestController;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.online.shelter.pet.spring_mvc.TestUtil.mockAuthorize;
import static com.online.shelter.pet.spring_mvc.UserTestData.USER;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.REPOSITORY_IMPLEMENTATION);
            appCtx.load("spring/spring-app.xml", "spring/spring-mock.xml");
            appCtx.refresh();

            mockAuthorize(USER);

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));
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
