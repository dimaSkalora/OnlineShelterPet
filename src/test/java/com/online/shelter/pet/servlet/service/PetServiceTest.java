package com.online.shelter.pet.servlet.service;

import com.online.shelter.pet.servlet.ActiveDbProfileResolver;
import com.online.shelter.pet.servlet.Profiles;
import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import static com.online.shelter.pet.servlet.PetTestData.*;
import static com.online.shelter.pet.servlet.UserTestData.ADMIN_ID;
import static com.online.shelter.pet.servlet.UserTestData.USER_ID;
import static org.slf4j.LoggerFactory.getLogger;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class PetServiceTest {
    private static final Logger log = getLogger("result");

    private static StringBuilder results = new StringBuilder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms\n");
        }
    };

    static {
        // needed only for java.util.logging (postgres driver)
        SLF4JBridgeHandler.install();
    }

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

    @Autowired
    private PetService service;

    @Test
    public void delete()throws Exception{
        service.delete(PET_ID, USER_ID);
        assertMatch(service.getAll(USER_ID),PET6,PET5,PET4,PET3,PET2);
    }

    @Test
    public void deleteNotFound()throws Exception{
        thrown.expect(NotFoundException.class);
        service.delete(PET_ID,1);
    }

    @Test
    public void create()throws Exception{
        Pet created = getCreated();
        service.create(created,USER_ID);
        assertMatch(service.getAll(USER_ID), created, PET6, PET5, PET4, PET3 , PET2, PET1);
    }


    @Test
    public void get() throws Exception {
        Pet actual = service.get(ADMIN_PET_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_PET1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(PET_ID, ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        Pet updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(PET_ID, USER_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + PET_ID);
        service.update(PET1, ADMIN_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), PETS);
    }

    @Test
    public void getBetween() throws Exception {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2018, Month.JANUARY, 29),
                LocalDate.of(2018, Month.JANUARY, 29), USER_ID), PET3, PET2, PET1);
    }

}
