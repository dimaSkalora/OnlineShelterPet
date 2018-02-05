package com.online.shelter.pet.servlet.repository.mock;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.repository.PetRepository;
import com.online.shelter.pet.servlet.util.PetUtil;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.online.shelter.pet.servlet.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static com.online.shelter.pet.servlet.repository.mock.InMemoryUserRepositoryImpl.USER_ID;

@Repository
public class InMemoryPetRepositoryImpl implements PetRepository {

    // Map  userId -> (petId-> pet)
    private Map<Integer, Map<Integer,Pet>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        //PetUtil.PETS.forEach(pet->save(pet));
        PetUtil.PETS.forEach(pet -> save(pet,USER_ID));
        save( new Pet(LocalDate.of(2018,Month.FEBRUARY,5)
                ,"Cat","caty","бразил","w","коричневый",
                1.8,18,4,"люда","0671234567","aeqe@eqwd"),ADMIN_ID);
        save( new Pet(LocalDate.of(2018,Month.FEBRUARY,5)
                ,"Dog","dogy","бразил","m","коричневый",
                1.8,15,4,"anna","0489449494","aeqsfsdre@eqwd"),ADMIN_ID);

    }
    @Override
    public Pet save(Pet pet, int userId) {
        Map<Integer,Pet> pets = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if(pet.isNew()){
            pet.setId(counter.incrementAndGet());
            pets.put(pet.getId(),pet);
            return pet;
        }
        // treat case: update, but absent in storage
        return pets.computeIfPresent(pet.getId(),(id,oldPet)->pet);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer,Pet> pets = repository.get(userId);
        return pets != null && pets.remove(id) != null;
    }

    @Override
    public Pet get(int id, int userId) {
        Map<Integer,Pet> pets = repository.get(userId);
        return pets != null ? null : pets.get(id);
    }

    @Override
    public List<Pet> getAll(int userId) {
        return getAllAsStream(userId).collect(Collectors.toList());
    }

    private Stream<Pet> getAllAsStream(int userId) {
        Map<Integer, Pet> pets = repository.get(userId);
        return pets == null ?
                Stream.empty() :
                pets.values().stream()
                        .sorted(Comparator.comparing(Pet::getCreatedDate).reversed());
    }
}
