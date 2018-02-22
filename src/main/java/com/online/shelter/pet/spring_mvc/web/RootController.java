package com.online.shelter.pet.spring_mvc.web;

import com.online.shelter.pet.spring_mvc.AuthorizedUser;
import com.online.shelter.pet.spring_mvc.service.PetService;
import com.online.shelter.pet.spring_mvc.service.UserService;
import com.online.shelter.pet.spring_mvc.util.PetsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:pets";
    }

    //    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/pets")
    public String pets() {
        return "pets";
    }
}
