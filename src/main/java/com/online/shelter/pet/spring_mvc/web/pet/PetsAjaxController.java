package com.online.shelter.pet.spring_mvc.web.pet;

import com.online.shelter.pet.spring_mvc.model.Pet;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ajax/profile/petsAllUsers")
public class PetsAjaxController extends AbstractPetController {
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pet> petsAllUsers() {
       // System.out.println("/ajax/profile/petsAllUsers "+super.petsAllUsers());
        return super.petsAllUsers();
    }
}
