package com.online.shelter.pet.spring_mvc.service;

import com.online.shelter.pet.spring_mvc.AuthorizedUser;
import com.online.shelter.pet.spring_mvc.model.User;
import com.online.shelter.pet.spring_mvc.repository.UserRepository;
import com.online.shelter.pet.spring_mvc.to.UserTo;
import com.online.shelter.pet.spring_mvc.util.UserUtil;
import com.online.shelter.pet.spring_mvc.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

import static com.online.shelter.pet.spring_mvc.util.UserUtil.prepareToSave;
import static com.online.shelter.pet.spring_mvc.util.UserUtil.updateFromTo;
import static com.online.shelter.pet.spring_mvc.util.ValidationUtil.checkNotFound;
import static com.online.shelter.pet.spring_mvc.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(userRepository.save(prepareToSave(user, passwordEncoder)), user.getId());
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        userRepository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    public User getWithPets(int id) {
        return checkNotFoundWithId(userRepository.getWithPets(id), id);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
