package com.online.shelter.pet.spring_mvc.web.pet;

import com.online.shelter.pet.spring_mvc.TestUtil;
import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.service.PetService;
import com.online.shelter.pet.spring_mvc.util.PetsUtil;
import com.online.shelter.pet.spring_mvc.web.AbstractControllerTest;
import com.online.shelter.pet.spring_mvc.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;


import java.util.Arrays;

import static com.online.shelter.pet.spring_mvc.PetTestData.*;
import static com.online.shelter.pet.spring_mvc.TestUtil.contentJson;
import static com.online.shelter.pet.spring_mvc.TestUtil.contentJsonArray;
import static com.online.shelter.pet.spring_mvc.UserTestData.USER;

import static com.online.shelter.pet.spring_mvc.model.AbstractBaseEntity.START_SEQ;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PetRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = PetRestControllerTest.REST_URL + '/';

    @Autowired
    private PetService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + PET_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(PET1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + PET_ID))
                .andExpect(status().isOk());
        assertMatch(service.getAll(START_SEQ), PET6, PET5, PET4, PET3, PET2);
    }

    @Test
    public void testUpdate() throws Exception {
        Pet updated = getUpdated();

        mockMvc.perform(put(REST_URL + PET_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(service.get(PET_ID, START_SEQ), updated);
    }

    @Test
    public void testCreate() throws Exception {
        Pet created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Pet returned = TestUtil.readFromJson(action, Pet.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(START_SEQ), created, PET6, PET5, PET4, PET3, PET2);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(PetsUtil.getWithDownplayWeight(PETS, USER.getNormalWeight())));
    }

    @Test
    public void testFilter() throws Exception {
        mockMvc.perform(get(REST_URL + "filter")
                .param("startDate", "2017-05-30").param("startTime", "07:00")
                .param("endDate", "2018-02-31").param("endTime", "11:00"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJsonArray(
                        PetsUtil.createWithDownplayWeight(PET4, true),
                        PetsUtil.createWithDownplayWeight(PET1, false)));
    }

    @Test
    public void testFilterAll() throws Exception {
        mockMvc.perform(get(REST_URL + "filter?startDate=&endTime="))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(PetsUtil.getWithDownplayWeight(Arrays.asList( PET6, PET5, PET4, PET3, PET2,PET1), USER.getNormalWeight())));
    }

}
