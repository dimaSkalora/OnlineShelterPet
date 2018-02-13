package com.online.shelter.pet.servlet.service;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;

import static com.online.shelter.pet.servlet.PetTestData.*;
import static com.online.shelter.pet.servlet.UserTestData.ADMIN_ID;
import static com.online.shelter.pet.servlet.UserTestData.USER_ID;

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

}
