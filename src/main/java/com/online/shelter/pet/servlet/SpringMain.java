package com.online.shelter.pet.servlet;

import com.online.shelter.pet.servlet.model.Role;
import com.online.shelter.pet.servlet.model.User;
import com.online.shelter.pet.servlet.repository.UserRepository;
import com.online.shelter.pet.servlet.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

//        UserRepository userRepository = (UserRepository) appCtx.getBean("mockUserRepository");
        UserRepository userRepository = appCtx.getBean(UserRepository.class);
        userRepository.getAll();

        UserService userService = appCtx.getBean(UserService.class);
        userService.create(new User(null,"userName","email","password", Role.ROLE_ADMIN));
        appCtx.close();
    }
}
