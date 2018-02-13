package com.online.shelter.pet.servlet.service.jdbc;

import com.online.shelter.pet.servlet.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.online.shelter.pet.servlet.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
