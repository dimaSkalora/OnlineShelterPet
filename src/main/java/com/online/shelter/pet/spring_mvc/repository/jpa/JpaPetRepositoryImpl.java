package com.online.shelter.pet.spring_mvc.repository.jpa;

import com.online.shelter.pet.spring_mvc.model.Pet;
import com.online.shelter.pet.spring_mvc.model.User;
import com.online.shelter.pet.spring_mvc.repository.PetRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaPetRepositoryImpl implements PetRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Pet save(Pet pet, int userId) {
       if(!pet.isNew() && get(pet.getId(), userId) == null)
           return null;
       pet.setUser(entityManager.getReference(User.class, userId));
       if(pet.isNew()){
           entityManager.persist(pet);
           return pet;
       }else
           return entityManager.merge(pet);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return entityManager.createNamedQuery(Pet.DELETE)
                .setParameter("id",id)
                .setParameter("userId",userId)
                .executeUpdate() != 0;
    }

    @Override
    public Pet get(int id, int userId) {
        Pet pet = entityManager.find(Pet.class, id);
        return pet != null && pet.getUser().getId() == userId ? pet : null;
    }

    @Override
    public List<Pet> getAll(int userId) {
        return entityManager.createNamedQuery(Pet.ALL_SORTED, Pet.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    @Override
    public List<Pet> getAll() {
        return entityManager.createNamedQuery(Pet.ALL_SORTED, Pet.class)
                .getResultList();
    }

    @Override
    public List<Pet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return entityManager.createNamedQuery(Pet.GET_BETWEEN, Pet.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }
}
