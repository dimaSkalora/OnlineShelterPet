package com.online.shelter.pet.servlet.repository;

import com.online.shelter.pet.servlet.model.Pet;

import java.util.Collection;

public interface PetRepository {
    Pet save(Pet pet);
    void delete(int id);
    Pet get(int id);
    Collection<Pet> getAll();
}
