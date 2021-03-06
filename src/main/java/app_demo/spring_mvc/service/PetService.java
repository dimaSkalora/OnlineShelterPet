package app_demo.spring_mvc.service;

import app_demo.spring_mvc.model.Pet;
import app_demo.spring_mvc.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface PetService {
    Pet get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    default List<Pet> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }

    List<Pet> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<Pet> getAll(int userId);
    List<Pet> petsAllUsers();

    Pet update(Pet pet, int userId) throws NotFoundException;

    Pet create(Pet pet, int userId);

    Pet getWithUser(int id, int userId);
}
