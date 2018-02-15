package com.online.shelter.pet.spring_mvc.repository.datajpa;

import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaPetRepositoryImpl implements PetRepository {

    @Autowired
    private CrudPetRepository crudPetRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Pet save(Pet pet, int userId) {
        if(!pet.isNew() && get(pet.getId(),userId) == null)
            return null;
        pet.setUser(crudUserRepository.getOne(userId));
        return crudPetRepository.save(pet);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudPetRepository.delete(id,userId) != 0;
    }

    @Override
    public Pet get(int id, int userId) {
        return crudPetRepository.findById(id).filter(pet->pet.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Pet> getAll(int userId) {
        return crudPetRepository.getAll(userId);
    }

    @Override
    public List<Pet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudPetRepository.getBetween(startDate, endDate, userId);
    }

    @Override
    public Pet getWithUser(int id, int userId) {
        return crudPetRepository.getWithUser(id, userId);
    }
}
