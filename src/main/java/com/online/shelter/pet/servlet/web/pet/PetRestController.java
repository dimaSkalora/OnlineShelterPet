package com.online.shelter.pet.servlet.web.pet;

import com.online.shelter.pet.servlet.AuthorizedUser;
import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.service.PetService;
import com.online.shelter.pet.servlet.to.PetWithDownplayWeight;
import com.online.shelter.pet.servlet.util.DateTimeUtil;
import com.online.shelter.pet.servlet.util.PetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static com.online.shelter.pet.servlet.util.ValidationUtil.assureIdConsistent;
import static com.online.shelter.pet.servlet.util.ValidationUtil.checkNew;

@Controller
public class PetRestController {
    private static final Logger log = LoggerFactory.getLogger(PetRestController.class);
    private final PetService service;

    @Autowired
    public PetRestController(PetService service) {
        this.service = service;
    }

    public Pet get(int id){
        int userId = AuthorizedUser.id();
        log.info("get pet {} for user {}",id,userId);
        return service.get(id,userId);
    }

    public void delete(int id){
        int userId = AuthorizedUser.id();
        log.info("delete pet {} for user {}",id,userId);
        service.delete(id,userId);
    }

    public List<PetWithDownplayWeight> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        return PetUtil.getWithDownplayWeight(service.getAll(userId), AuthorizedUser.getNormalWeight());
    }

    public Pet create(Pet pet) {
        int userId = AuthorizedUser.id();
        checkNew(pet);
        log.info("create {} for user {}", pet, userId);
        return service.create(pet, userId);
    }

    public void update(Pet pet, int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(pet, id);
        log.info("update {} for user {}", pet, userId);
        service.update(pet, userId);
    }

    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */
    public List<PetWithDownplayWeight> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

        List<Pet> mealsDateFiltered = service.getBetweenDates(
                startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId);

        return PetUtil.getFilteredWithDownplayWeight(mealsDateFiltered,
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX, Arrays.asList("Cat","Dog","Others"),
                AuthorizedUser.getNormalWeight()
        );
    }
}