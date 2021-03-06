package app_demo.spring_mvc.repository;

import app_demo.spring_mvc.model.Pet;

import java.time.LocalDateTime;
import java.util.List;

public interface PetRepository {
    // null if updated pet do not belong to userId
    Pet save(Pet pet, int userId);

    // false if pet do not belong to userId
    boolean delete(int id, int userId);

    // null if pet do not belong to userId
    Pet get(int id, int userId);

    // ORDERED dateTime desc
    List<Pet> getAll(int userId);

    // ORDERED dateTime desc
    List<Pet> getAll();

    // ORDERED dateTime desc
    List<Pet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    default Pet getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
