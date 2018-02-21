package com.online.shelter.pet.spring_mvc.web;

import com.online.shelter.pet.spring_mvc.AuthorizedUser;
import com.online.shelter.pet.spring_mvc.service.PetService;
import com.online.shelter.pet.spring_mvc.service.UserService;
import com.online.shelter.pet.spring_mvc.util.PetsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @Autowired
    private PetService petService;

    @GetMapping("/")
    public String root() {
        return "redirect:pets";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/pets")
    public String pets(Model model) {
        model.addAttribute("pets",
                PetsUtil.getWithDownplayWeight(petService.getAll(AuthorizedUser.id()), AuthorizedUser.getDownplayWeight()));
        return "pets";
    }
}
