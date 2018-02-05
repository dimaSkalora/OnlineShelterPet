package com.online.shelter.pet.servlet.web.user;

import com.online.shelter.pet.servlet.AuthorizedUser;
import com.online.shelter.pet.servlet.model.User;
import org.springframework.stereotype.Controller;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(AuthorizedUser.id());
    }

    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    public void update(User user) {
        super.update(user, AuthorizedUser.id());
    }
}
