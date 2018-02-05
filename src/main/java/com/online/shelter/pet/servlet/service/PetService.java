package com.online.shelter.pet.servlet.service;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;

import java.util.List;

public interface PetService {
    Pet get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    List<Pet> getAll(int userId);

    Pet update(Pet pet, int userId) throws NotFoundException;

    Pet create(Pet pet, int userId);
}
