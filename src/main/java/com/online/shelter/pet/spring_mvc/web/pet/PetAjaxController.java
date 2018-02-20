package com.online.shelter.pet.spring_mvc.web.pet;

import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.to.PetWithDownplayWeight;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/profile/pets")
public class PetAjaxController  extends AbstractPetController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PetWithDownplayWeight> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("createdDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdDate,
                               @RequestParam("typePet") String typePet,
                               @RequestParam("namePet") String namePet,
                               @RequestParam("breed") String breed,
                               @RequestParam("sex") String sex,
                               @RequestParam("color") String color,
                               @RequestParam("age") double age,
                               @RequestParam("growth") int growth,
                               @RequestParam("weight") double weight,
                               @RequestParam("namePerson") String namePerson,
                               @RequestParam("phone") String phone,
                               @RequestParam("email") String email) {
        Pet pet = new Pet(id, createdDate, typePet, namePet, breed,sex, color, age,
                growth, weight, namePerson, phone, email);
        if (pet.isNew()) {
            super.create(pet);
        }
    }

    @Override
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PetWithDownplayWeight> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }

}
