package com.online.shelter.pet.servlet.service;

import com.online.shelter.pet.servlet.model.User;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);
    void delete(int id) throws NotFoundException;
    User get(int id) throws NotFoundException;
    User getByEmail(String email) throws NotFoundException;
    void update(User user);
    List<User> getAll();
}