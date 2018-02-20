package com.online.shelter.pet.spring_mvc.util;

import com.online.shelter.pet.spring_mvc.model.Role;
import com.online.shelter.pet.spring_mvc.model.User;
import com.online.shelter.pet.spring_mvc.to.UserTo;

public class UserUtil {

    public static final double DEFAULT_NOLMAL_WEIGHT = 0.2;

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }
}
