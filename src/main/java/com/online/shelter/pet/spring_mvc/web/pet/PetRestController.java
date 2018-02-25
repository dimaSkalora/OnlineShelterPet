package com.online.shelter.pet.spring_mvc.web.pet;

import com.online.shelter.pet.spring_mvc.View;
import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.to.PetWithDownplayWeight;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = PetRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PetRestController extends AbstractPetController {
    static final String REST_URL = "/rest/profile/pets";

    @Override
    @GetMapping("/{id}")
    public Pet get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<PetWithDownplayWeight> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.Web.class)  @RequestBody Pet pet, @PathVariable("id") int id) {
        super.update(pet, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> createWithLocation(@Validated(View.Web.class)  @RequestBody Pet pet) {
        Pet created = super.create(pet);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @GetMapping(value = "/filter")
    public List<PetWithDownplayWeight> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}