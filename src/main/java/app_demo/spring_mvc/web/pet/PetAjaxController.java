package app_demo.spring_mvc.web.pet;

import app_demo.spring_mvc.model.Pet;
import app_demo.spring_mvc.to.PetWithDownplayWeight;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/profile/pets")
public class PetAjaxController extends AbstractPetController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PetWithDownplayWeight> getAll() {
        System.out.println("/ajax/profile/pets "+super.getAll());
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public Pet get(@PathVariable("id") int id) {
        System.out.println("/ajax/profile/pets "+super.get(100001));
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid Pet pet) {
        if (pet.isNew()) {
            super.create(pet);
        } else {
            super.update(pet, pet.getId());
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
