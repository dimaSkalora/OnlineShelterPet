package com.online.shelter.pet.servlet.repository;

import com.online.shelter.pet.servlet.model.Pet;

import java.time.LocalDateTime;
import java.util.List;

public interface PetRepository {
    // null if updated pet do not belong to userId
    Pet save(Pet pet, int userId);

    // false if pet do not belong to userId
    boolean delete(int id, int userId);

    // null if pet do not belong to userId
    Pet get(int id, int userId);

    // ORDERED dateTime desc
    List<Pet> getAll(int userId);

    // ORDERED dateTime desc
    List<Pet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

}
