package com.online.shelter.pet.servlet.repository;

import com.online.shelter.pet.servlet.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    // false if not found
    boolean delete(int id);
    // null if not found
    User get(int id);
    // null if not found
    User getByEmail(String email);
    List<User> getAll();
    default User getWithPets(int id){
        throw new UnsupportedOperationException();
    }
}
