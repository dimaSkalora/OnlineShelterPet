package com.online.shelter.pet.spring_mvc.web.pet;

import com.online.shelter.pet.spring_mvc.AuthorizedUser;
import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.service.PetService;
import com.online.shelter.pet.spring_mvc.to.PetWithDownplayWeight;
import com.online.shelter.pet.spring_mvc.util.DateTimeUtil;
import com.online.shelter.pet.spring_mvc.util.PetsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static com.online.shelter.pet.spring_mvc.util.ValidationUtil.assureIdConsistent;
import static com.online.shelter.pet.spring_mvc.util.ValidationUtil.checkNew;

public class AbstractPetController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PetService service;

    public Pet get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get meal {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<PetWithDownplayWeight> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        return PetsUtil.getWithDownplayWeight(service.getAll(userId), AuthorizedUser.getNormalWeight());
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

        return PetsUtil.getFilteredWithDownplayWeight(mealsDateFiltered,
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX, Arrays.asList("Cat","Dog","Others"),
                AuthorizedUser.getNormalWeight()
        );
    }
}
