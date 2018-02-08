package com.online.shelter.pet.servlet.service;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Month;

import static com.online.shelter.pet.servlet.PetTestData.*;
import static com.online.shelter.pet.servlet.UserTestData.ADMIN_ID;
import static com.online.shelter.pet.servlet.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class PetServiceTest {
    static{
        SLF4JBridgeHandler.install();
    }

    private PetService service;

    @Test
    public void testDelete()throws Exception{
        service.delete(PET_ID, USER_ID);
        assertMatch(service.getAll(USER_ID),PET6,PET5,PET4,PET3,PET2);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound()throws Exception{
        service.delete(PET_ID,1);
    }

    @Test
    public void testSave()throws Exception{
        Pet created = getCreated();
        service.create(created,USER_ID);
        assertMatch(service.getAll(USER_ID), created, PET6, PET5, PET4, PET3 , PET2, PET1);
    }


    @Test
    public void testGet() throws Exception {
        Pet actual = service.get(ADMIN_PET_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_PET1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(PET_ID, ADMIN_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Pet updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(PET_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        service.update(PET1, ADMIN_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        assertMatch(service.getAll(USER_ID), PETS);
    }

    @Test
    public void testGetBetween() throws Exception {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2018, Month.JANUARY, 29),
                LocalDate.of(2018, Month.JANUARY, 29), USER_ID), PET3, PET2, PET1);
    }

}
