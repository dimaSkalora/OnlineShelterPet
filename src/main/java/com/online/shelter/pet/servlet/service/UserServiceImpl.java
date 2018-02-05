package com.online.shelter.pet.servlet.service;

import com.online.shelter.pet.servlet.model.User;
import com.online.shelter.pet.servlet.repository.UserRepository;
import com.online.shelter.pet.servlet.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.online.shelter.pet.servlet.util.ValidationUtil.checkNotFound;
import static com.online.shelter.pet.servlet.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;

  /*  public void setRepository(UserRepository repository) {
        this.repository = repository;
    }*/

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }
}