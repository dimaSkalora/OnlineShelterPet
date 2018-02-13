package com.online.shelter.pet.servlet.service.datajpa;

import com.online.shelter.pet.servlet.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.online.shelter.pet.servlet.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
