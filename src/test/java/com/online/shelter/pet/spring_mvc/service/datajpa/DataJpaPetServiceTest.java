package com.online.shelter.pet.spring_mvc.service.datajpa;

import com.online.shelter.pet.spring_mvc.UserTestData;
import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.service.AbstractPetlServiceTest;
import com.online.shelter.pet.spring_mvc.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.online.shelter.pet.spring_mvc.PetTestData.*;
import static com.online.shelter.pet.spring_mvc.Profiles.DATAJPA;
import static com.online.shelter.pet.spring_mvc.UserTestData.ADMIN_ID;

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
