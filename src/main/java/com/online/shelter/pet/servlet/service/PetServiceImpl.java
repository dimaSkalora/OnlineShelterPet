package com.online.shelter.pet.servlet.service;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.repository.PetRepository;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import static com.online.shelter.pet.servlet.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository repository;

    @Autowired
    public PetServiceImpl(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pet get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id,userId),id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.get(id,userId),id);
    }

    @Override
    public List<Pet> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<Pet> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Pet update(Pet pet, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(pet,userId),pet.getId());
    }

    @Override
    public Pet create(Pet pet, int userId) {
        return repository.save(pet,userId);
    }
}
