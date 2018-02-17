package com.online.shelter.pet.spring_mvc.web.json;

import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.web.json.JsonUtil;
import org.junit.Test;

import java.util.List;

import static com.online.shelter.pet.spring_mvc.PetTestData.ADMIN_PET1;
import static com.online.shelter.pet.spring_mvc.PetTestData.PETS;
import static com.online.shelter.pet.spring_mvc.PetTestData.assertMatch;


public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(ADMIN_PET1);
        System.out.println(json);
        Pet pet = JsonUtil.readValue(json, Pet.class);
        assertMatch(pet, ADMIN_PET1);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(PETS);
        System.out.println(json);
        List<Pet> pets = JsonUtil.readValues(json, Pet.class);
        assertMatch(pets, PETS);
    }
}
