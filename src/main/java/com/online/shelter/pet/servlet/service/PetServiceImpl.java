package com.online.shelter.pet.servlet.service;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.repository.PetRepository;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import static com.online.shelter.pet.servlet.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(petRepository.get(id,userId),id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(petRepository.get(id,userId),id);
    }

    @Override
    public List<Pet> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return petRepository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<Pet> getAll(int userId) {
        return petRepository.getAll(userId);
    }

    @Override
    public Pet update(Pet pet, int userId) throws NotFoundException {
        return checkNotFoundWithId(petRepository.save(pet,userId),pet.getId());
    }

    @Override
    public Pet create(Pet pet, int userId) {
        Assert.notNull(pet, "meal must not be null");
        return petRepository.save(pet,userId);
    }

    @Override
    public Pet getWithUser(int id, int userId) {
        return checkNotFoundWithId(petRepository.getWithUser(id, userId), id);
    }
}
