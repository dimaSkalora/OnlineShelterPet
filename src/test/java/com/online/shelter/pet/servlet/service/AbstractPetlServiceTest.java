package com.online.shelter.pet.servlet.service;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;

import static com.online.shelter.pet.servlet.PetTestData.*;
import static com.online.shelter.pet.servlet.UserTestData.ADMIN_ID;
import static com.online.shelter.pet.servlet.UserTestData.USER_ID;
import static java.time.LocalDateTime.of;

public abstract class AbstractPetlServiceTest extends AbstractServiceTest{

    @Autowired
    protected PetService service;

    @Test
    public void delete()throws Exception{
        service.delete(PET_ID, USER_ID);
        assertMatch(service.getAll(USER_ID),PET6,PET5,PET4,PET3,PET2);
    }

    @Test
    public void deleteNotFound()throws Exception{
        thrown.expect(NotFoundException.class);
        service.delete(PET_ID,1);
    }

    @Test
    public void create()throws Exception{
        Pet created = getCreated();
        service.create(created,USER_ID);
        assertMatch(service.getAll(USER_ID), created, PET6, PET5, PET4, PET3 , PET2, PET1);
    }


    @Test
    public void get() throws Exception {
        Pet actual = service.get(ADMIN_PET_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_PET1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(PET_ID, ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        Pet updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(PET_ID, USER_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + PET_ID);
        service.update(PET1, ADMIN_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), PETS);
    }

    @Test
    public void getBetween() throws Exception {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2018, Month.JANUARY, 29),
                LocalDate.of(2018, Month.JANUARY, 29), USER_ID), PET3, PET2, PET1);
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.create(new Pet(null, of(2018, Month.FEBRUARY, 1, 18, 0),"Dog","d1","таксф","w","чорный",1.5,40,16,"петя","0631234567","gui@ki"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Pet(null, null, "Cat","c1","сиамский","m","белый",2,20,4,"вася","0731234567","sadr@saae"), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Pet(null, of(2018, Month.FEBRUARY, 1, 18, 0), "Cat","c1","сеамс","m","белый",1,15,3.5,"катф","0671234567","arfva@dav"), USER_ID), ConstraintViolationException.class);
    }

}
