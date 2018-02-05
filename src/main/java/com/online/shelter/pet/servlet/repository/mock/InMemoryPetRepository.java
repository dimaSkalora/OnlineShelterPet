package com.online.shelter.pet.servlet.repository.mock;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.repository.PetRepository;
import com.online.shelter.pet.servlet.util.PetUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPetRepository implements PetRepository {
    private Map<Integer, Pet> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        //PetUtil.PETS.forEach(this::save);
        PetUtil.PETS.forEach(pet->save(pet));
    }
    @Override
    public Pet save(Pet pet) {
        if(pet.isNew())
            pet.setId(counter.incrementAndGet());
        repository.put(pet.getId(),pet);
        return pet;
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Pet get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Pet> getAll() {
        return repository.values();
    }
}
