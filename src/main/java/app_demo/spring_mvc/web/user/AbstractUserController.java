package app_demo.spring_mvc.web.user;

import app_demo.spring_mvc.model.User;
import app_demo.spring_mvc.service.UserService;
import app_demo.spring_mvc.to.UserTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static app_demo.spring_mvc.util.ValidationUtil.assureIdConsistent;
import static app_demo.spring_mvc.util.ValidationUtil.checkNew;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        System.out.println("getAll "+service.getAll());
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        System.out.println("get "+service.get(id));
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        System.out.println("create "+service.create(user));
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        System.out.println("delete "+id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        System.out.println("update "+user+" "+id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        System.out.println("update "+userTo+" "+id);
        service.update(userTo);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        System.out.println("getByEmail "+service.getByEmail(email));
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info((enabled ? "enable " : "disable ") + id);
        System.out.println("enable "+id+" "+enabled);
        service.enable(id, enabled);
    }
}

