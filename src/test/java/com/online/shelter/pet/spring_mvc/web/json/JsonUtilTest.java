package com.online.shelter.pet.spring_mvc.web.json;

import com.online.shelter.pet.spring_mvc.UserTestData;
import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.model.User;
import com.online.shelter.pet.spring_mvc.web.json.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.online.shelter.pet.spring_mvc.PetTestData.ADMIN_PET1;
import static com.online.shelter.pet.spring_mvc.PetTestData.PETS;
import static com.online.shelter.pet.spring_mvc.PetTestData.assertMatch;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;


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

    @Test
    public void testWriteOnlyAccess() throws Exception {
        String json = JsonUtil.writeValue(UserTestData.USER);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.USER, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        Assert.assertEquals(user.getPassword(), "newPass");
    }
}
