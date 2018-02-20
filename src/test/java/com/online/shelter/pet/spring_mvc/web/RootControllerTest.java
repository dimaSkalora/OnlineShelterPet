package com.online.shelter.pet.spring_mvc.web;

import com.online.shelter.pet.spring_mvc.AuthorizedUser;
import com.online.shelter.pet.spring_mvc.PetTestData;
import com.online.shelter.pet.spring_mvc.util.PetsUtil;
import org.junit.Test;

import static com.online.shelter.pet.spring_mvc.UserTestData.USER;
import static com.online.shelter.pet.spring_mvc.model.AbstractBaseEntity.START_SEQ;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(USER.getName()))
                        )
                )));
    }

    @Test
    public void testMeals() throws Exception {
        mockMvc.perform(get("/pets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("pets"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/pets.jsp"))
                .andExpect(model().attribute("pets", PetsUtil.getWithDownplayWeight(PetTestData.PETS, AuthorizedUser.getNormalWeight())));
    }
}