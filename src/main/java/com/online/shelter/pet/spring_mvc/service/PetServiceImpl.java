package com.online.shelter.pet.spring_mvc.service;

import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.repository.PetRepository;
import com.online.shelter.pet.spring_mvc.util.exception.NotFoundException;
import com.online.shelter.pet.spring_mvc.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import static com.online.shelter.pet.spring_mvc.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet get(int id, int userId) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(petRepository.get(id,userId),id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(petRepository.get(id,userId),id);
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
    public List<Pet> petsAllUsers() {
        return petRepository.getAll();
    }

    @Override
    public Pet update(Pet pet, int userId) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(petRepository.save(pet,userId),pet.getId());
    }

    @Override
    public Pet create(Pet pet, int userId) {
        Assert.notNull(pet, "meal must not be null");
        return petRepository.save(pet,userId);
    }

    @Override
    public Pet getWithUser(int id, int userId) {
        return ValidationUtil.checkNotFoundWithId(petRepository.getWithUser(id, userId), id);
    }
}
