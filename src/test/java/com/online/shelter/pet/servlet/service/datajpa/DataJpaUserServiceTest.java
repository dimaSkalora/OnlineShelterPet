package com.online.shelter.pet.servlet.service.datajpa;

import com.online.shelter.pet.servlet.PetTestData;
import com.online.shelter.pet.servlet.model.User;
import com.online.shelter.pet.servlet.service.AbstractUserServiceTest;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.online.shelter.pet.servlet.Profiles.DATAJPA;
import static com.online.shelter.pet.servlet.UserTestData.USER;
import static com.online.shelter.pet.servlet.UserTestData.USER_ID;
import static com.online.shelter.pet.servlet.UserTestData.assertMatch;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void testGetWithMeals() throws Exception {
        User user = service.getWithPets(USER_ID);
        assertMatch(user, USER);
        PetTestData.assertMatch(user.getPets(), PetTestData.PETS);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithMealsNotFound() throws Exception {
        service.getWithPets(1);
    }
}
