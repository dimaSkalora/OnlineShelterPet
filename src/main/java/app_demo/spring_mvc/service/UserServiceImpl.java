package app_demo.spring_mvc.service;

import app_demo.spring_mvc.AuthorizedUser;
import app_demo.spring_mvc.model.Role;
import app_demo.spring_mvc.model.User;
import app_demo.spring_mvc.repository.UserRepository;
import app_demo.spring_mvc.to.UserTo;
import app_demo.spring_mvc.util.UserUtil;
import app_demo.spring_mvc.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static app_demo.spring_mvc.util.UserUtil.prepareToSave;
import static app_demo.spring_mvc.util.UserUtil.updateFromTo;
import static app_demo.spring_mvc.util.ValidationUtil.checkNotFound;
import static app_demo.spring_mvc.util.ValidationUtil.checkNotFoundWithId;


@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }

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

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(userRepository.save(prepareToSave(user, passwordEncoder)), user.getId());
    }

    @Override
    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        userRepository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
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
        System.out.println("loadUserByUsername "+user.toString());
        return new AuthorizedUser(user);
    }
}
