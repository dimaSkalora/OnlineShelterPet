package com.online.shelter.pet.spring_mvc.service.datajpa;

import com.online.shelter.pet.spring_mvc.PetTestData;
import com.online.shelter.pet.spring_mvc.model.User;
import com.online.shelter.pet.spring_mvc.service.AbstractJpaUserServiceTest;
import com.online.shelter.pet.spring_mvc.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.online.shelter.pet.spring_mvc.Profiles.DATAJPA;
import static com.online.shelter.pet.spring_mvc.UserTestData.USER;
import static com.online.shelter.pet.spring_mvc.UserTestData.USER_ID;
import static com.online.shelter.pet.spring_mvc.UserTestData.assertMatch;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {
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
