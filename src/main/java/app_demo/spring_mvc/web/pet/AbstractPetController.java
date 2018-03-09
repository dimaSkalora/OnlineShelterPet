package app_demo.spring_mvc.web.pet;

import app_demo.spring_mvc.AuthorizedUser;
import app_demo.spring_mvc.model.Pet;
import app_demo.spring_mvc.service.PetService;
import app_demo.spring_mvc.to.PetWithDownplayWeight;
import app_demo.spring_mvc.util.DateTimeUtil;
import app_demo.spring_mvc.util.PetsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static app_demo.spring_mvc.util.ValidationUtil.assureIdConsistent;
import static app_demo.spring_mvc.util.ValidationUtil.checkNew;

public class AbstractPetController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PetService service;

    public Pet get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get pet {} for user {}", id, userId);
        System.out.println("get pet {} for user {}"+service.get(id,userId));
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete pet {} for user {}", id, userId);
        System.out.println("delete pet {} for user {}"+service.get(id,userId));
        service.delete(id, userId);
    }

    public List<PetWithDownplayWeight> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        System.out.println("getAll for user {}"+PetsUtil.getWithDownplayWeight(service.getAll(userId), AuthorizedUser.getDownplayWeight()));
        return PetsUtil.getWithDownplayWeight(service.getAll(userId), AuthorizedUser.getDownplayWeight());
    }

    public List<Pet> petsAllUsers(){
        log.info("petsAllUsers for user {}");
       System.out.println("petsAllUsers for user {} "+PetsUtil.getPetAll(service.petsAllUsers()));
        return PetsUtil.getPetAll(service.petsAllUsers());
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
                AuthorizedUser.getDownplayWeight()
        );
    }
}
