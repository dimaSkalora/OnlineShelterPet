package com.online.shelter.pet.spring_mvc.web;

import com.online.shelter.pet.spring_mvc.AuthorizedUser;
import com.online.shelter.pet.spring_mvc.PetTestData;
import com.online.shelter.pet.spring_mvc.util.PetsUtil;
import org.junit.Test;

import static com.online.shelter.pet.spring_mvc.TestUtil.userAuth;
import static com.online.shelter.pet.spring_mvc.UserTestData.ADMIN;
import static com.online.shelter.pet.spring_mvc.UserTestData.USER;
import static com.online.shelter.pet.spring_mvc.model.AbstractBaseEntity.START_SEQ;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testPets() throws Exception {
        mockMvc.perform(get("/pets")
                .with(userAuth(USER)))
                .andDo(print())
                .andExpect(view().name("pets"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/pets.jsp"))
                .andExpect(model().attribute("pets", PetsUtil.getWithDownplayWeight(PetTestData.PETS, USER.getNormalWeight())));
    }
}
