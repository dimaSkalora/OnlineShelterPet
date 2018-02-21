package com.online.shelter.pet.spring_mvc.service;

import com.online.shelter.pet.spring_mvc.model.User;
import com.online.shelter.pet.spring_mvc.to.UserTo;
import com.online.shelter.pet.spring_mvc.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);
    void delete(int id) throws NotFoundException;
    User get(int id) throws NotFoundException;
    User getByEmail(String email) throws NotFoundException;
    void update(User user);
    void update(UserTo user);

    List<User> getAll();

    void enable(int id, boolean enable);

    User getWithPets(int id);
}
