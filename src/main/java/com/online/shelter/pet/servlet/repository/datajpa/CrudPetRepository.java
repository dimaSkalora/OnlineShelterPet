package com.online.shelter.pet.servlet.repository.datajpa;

import com.online.shelter.pet.servlet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface CrudPetRepository extends JpaRepository<Pet, Integer> {

    @Modifying
    @Transactional
    @Query("delete from Pet p where p.id = :id and p.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    Pet save(Pet item);

    @Query("select p from Pet p where p.user.id=:userId order by p.createdDate desc ")
    List<Pet> getAll(@Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT p from Pet p WHERE p.user.id=:userId AND p.createdDate BETWEEN :startDate AND :endDate ORDER BY p.createdDate DESC")
    List<Pet> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);
}
