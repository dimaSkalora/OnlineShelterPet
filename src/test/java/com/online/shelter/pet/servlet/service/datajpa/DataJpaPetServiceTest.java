package com.online.shelter.pet.servlet.service.datajpa;

import com.online.shelter.pet.servlet.UserTestData;
import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.service.AbstractPetlServiceTest;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.online.shelter.pet.servlet.PetTestData.*;
import static com.online.shelter.pet.servlet.Profiles.DATAJPA;
import static com.online.shelter.pet.servlet.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
public class DataJpaPetServiceTest extends AbstractPetlServiceTest {
    @Test
    public void testGetWithUser() throws Exception {
        Pet adminMeal = service.getWithUser(ADMIN_PET_ID, ADMIN_ID);
        assertMatch(adminMeal, ADMIN_PET1);
        UserTestData.assertMatch(adminMeal.getUser(), UserTestData.ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithUserNotFound() throws Exception {
        service.getWithUser(PET_ID, ADMIN_ID);
    }
}
