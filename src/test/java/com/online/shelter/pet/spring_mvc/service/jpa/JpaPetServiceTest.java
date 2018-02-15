package com.online.shelter.pet.spring_mvc.service.jpa;

import com.online.shelter.pet.spring_mvc.service.AbstractPetlServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.online.shelter.pet.spring_mvc.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaPetServiceTest extends AbstractPetlServiceTest {
}
