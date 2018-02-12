package com.online.shelter.pet.servlet.repository.datajpa;

import com.online.shelter.pet.servlet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudPetRepository extends JpaRepository<Pet, Integer> {
}
