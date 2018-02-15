package com.online.shelter.pet.spring_mvc.service.jdbc;

import com.online.shelter.pet.spring_mvc.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.online.shelter.pet.spring_mvc.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
