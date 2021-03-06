package com.online.shelter.pet.spring_mvc.web.pet;

import com.online.shelter.pet.spring_mvc.TestUtil;
import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.service.PetService;
import com.online.shelter.pet.spring_mvc.util.PetsUtil;
import com.online.shelter.pet.spring_mvc.util.exception.ErrorType;
import com.online.shelter.pet.spring_mvc.web.AbstractControllerTest;
import com.online.shelter.pet.spring_mvc.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Arrays;

import static com.online.shelter.pet.spring_mvc.PetTestData.*;
import static com.online.shelter.pet.spring_mvc.TestUtil.contentJson;
import static com.online.shelter.pet.spring_mvc.TestUtil.contentJsonArray;
import static com.online.shelter.pet.spring_mvc.TestUtil.userHttpBasic;
import static com.online.shelter.pet.spring_mvc.UserTestData.ADMIN;
import static com.online.shelter.pet.spring_mvc.UserTestData.USER;

import static com.online.shelter.pet.spring_mvc.model.AbstractBaseEntity.START_SEQ;
import static com.online.shelter.pet.spring_mvc.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_DATETIME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(START_SEQ), PET6, PET5, PET4, PET3, PET2);
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_PET_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity());
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
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + ADMIN_PET_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
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


    @Test
    public void testUpdateHtmlUnsafe() throws Exception {
        Pet invalid = new Pet(PET_ID, LocalDateTime.now(), "<script>alert(123)</script>", "sfvd","cfw","wcs","rwcs",0.5,10,0.6,"ii0","jygf","dhnf");
        mockMvc.perform(put(REST_URL + PET_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }


    @Test
    public void testCreateInvalid() throws Exception {
        Pet invalid = new Pet(null, null, "Dummy", "sfvd","cfw","wcs","rwcs",0.5,10,0.6,"ii0","jygf","dhnf");
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Pet invalid = new Pet(PET_ID, null,  "Dummy", "sfvd",null,"wcs","rwcs",0.5,10,0.6,"ii0","jygf","dhnf");
        mockMvc.perform(put(REST_URL + PET_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                //.andExpect(jsonPath("$.type").value(ErrorType.VALIDATION_ERROR.name()))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testUpdateDuplicate() throws Exception {
        Pet invalid = new Pet(PET_ID, PET2.getCreatedDate(), "Dummy","sfvd","cfw","wcs","rwcs",0.5,10,0.6,"ii0","jygf","dhnf");

        mockMvc.perform(put(REST_URL + PET_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR))
                .andExpect(jsonMessage("$.details", EXCEPTION_DUPLICATE_DATETIME));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testCreateDuplicate() throws Exception {
        Pet invalid = new Pet(null, ADMIN_PET1.getCreatedDate(), "Dummy", "sfvd","cfw","wcs","rwcs",0.5,10,0.6,"ii0","jygf","dhnf");
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR))
                .andExpect(jsonMessage("$.details", EXCEPTION_DUPLICATE_DATETIME));
    }

}
