package com.online.shelter.pet.spring_mvc.service.jdbc;

import com.online.shelter.pet.spring_mvc.service.AbstractPetlServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.online.shelter.pet.spring_mvc.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcPetServiceTest extends AbstractPetlServiceTest {
}
