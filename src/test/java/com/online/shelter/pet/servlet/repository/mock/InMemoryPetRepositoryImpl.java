package com.online.shelter.pet.servlet.repository.mock;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.repository.PetRepository;
import com.online.shelter.pet.servlet.util.DateTimeUtil;
import com.online.shelter.pet.servlet.util.PetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.online.shelter.pet.servlet.UserTestData.ADMIN_ID;
import static com.online.shelter.pet.servlet.UserTestData.USER_ID;


@Repository
public class InMemoryPetRepositoryImpl implements PetRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    // Map  userId -> (petId-> pet)
    private Map<Integer, Map<Integer,Pet>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        //PetUtil.PETS.forEach(pet->save(pet));
        PetUtil.PETS.forEach(pet -> save(pet,USER_ID));
        save( new Pet(LocalDateTime.of(2018,Month.FEBRUARY,5,10,0)
                ,"Cat","caty","бразил","w","коричневый",
                1.8,18,4,"люда","0671234567","aeqe@eqwd"),ADMIN_ID);
        save( new Pet(LocalDateTime.of(2018,Month.FEBRUARY,5,18,0)
                ,"Dog","dogy","бразил","m","коричневый",
                1.8,15,4,"anna","0489449494","aeqsfsdre@eqwd"),ADMIN_ID);

    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
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
        return getAllFiltered(userId,pet -> true);
    }

    @Override
    public List<Pet> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getAllFiltered(userId, pet ->DateTimeUtil.isBetween(pet.getCreatedDate(), startDateTime,endDateTime));
    }

    private List<Pet> getAllFiltered(int userId, Predicate<Pet> filter) {
        Map<Integer, Pet> pets = repository.get(userId);
        return CollectionUtils.isEmpty(pets) ? Collections.emptyList() :
                pets.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Pet::getCreatedDate).reversed())
                        .collect(Collectors.toList());
    }
}
