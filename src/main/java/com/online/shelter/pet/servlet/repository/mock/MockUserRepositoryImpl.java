package com.online.shelter.pet.servlet.repository.mock;

import com.online.shelter.pet.servlet.model.User;
import com.online.shelter.pet.servlet.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class MockUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(MockUserRepositoryImpl.class);

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return true;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return null;
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return Collections.emptyList();
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return null;
    }
}
