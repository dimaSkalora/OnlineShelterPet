package com.online.shelter.pet.servlet.repository.datajpa;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaPetRepositoryImpl implements PetRepository {

    @Autowired
    private CrudPetRepository crudPetRepository;

    @Override
    public Pet save(Pet pet, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Pet get(int id, int userId) {
        return null;
    }

    @Override
    public List<Pet> getAll(int userId) {
        return null;
    }

    @Override
    public List<Pet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
