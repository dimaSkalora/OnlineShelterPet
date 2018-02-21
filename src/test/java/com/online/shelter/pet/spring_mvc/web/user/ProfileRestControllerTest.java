package com.online.shelter.pet.spring_mvc.web.user;

import com.online.shelter.pet.spring_mvc.TestUtil;
import com.online.shelter.pet.spring_mvc.model.Role;
import com.online.shelter.pet.spring_mvc.model.User;
import com.online.shelter.pet.spring_mvc.to.UserTo;
import com.online.shelter.pet.spring_mvc.util.UserUtil;
import com.online.shelter.pet.spring_mvc.web.AbstractControllerTest;
import com.online.shelter.pet.spring_mvc.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.http.MediaType;

import static com.online.shelter.pet.spring_mvc.TestUtil.userHttpBasic;
import static com.online.shelter.pet.spring_mvc.UserTestData.*;
import static com.online.shelter.pet.spring_mvc.web.user.ProfileRestController.REST_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProfileRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(USER)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(USER))
        );
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN);
    }

    @Test
    public void testUpdate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword", 0.2);

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isOk());

        assertMatch(userService.getByEmail("newemail@ya.ru"), UserUtil.updateFromTo(new User(USER), updatedTo));
    }
}
