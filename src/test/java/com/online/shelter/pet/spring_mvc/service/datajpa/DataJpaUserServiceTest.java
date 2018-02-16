package com.online.shelter.pet.spring_mvc.service.datajpa;

import com.online.shelter.pet.spring_mvc.PetTestData;
import com.online.shelter.pet.spring_mvc.model.User;
import com.online.shelter.pet.spring_mvc.service.AbstractJpaUserServiceTest;
import com.online.shelter.pet.spring_mvc.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.online.shelter.pet.spring_mvc.Profiles.DATAJPA;
import static com.online.shelter.pet.spring_mvc.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {
    @Test
    public void testGetWithMeals() throws Exception {
        User admin = service.getWithPets(ADMIN_ID);
        assertMatch(admin, ADMIN);
        PetTestData.assertMatch(admin.getPets(), PetTestData.ADMIN_PET2, PetTestData.ADMIN_PET1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithMealsNotFound() throws Exception {
        service.getWithPets(1);
    }
}
