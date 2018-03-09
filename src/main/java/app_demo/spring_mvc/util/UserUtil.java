package app_demo.spring_mvc.util;

import app_demo.spring_mvc.model.Role;
import app_demo.spring_mvc.model.User;
import app_demo.spring_mvc.to.UserTo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class UserUtil {

    public static final double DEFAULT_NOLMAL_WEIGHT = 0.2;

    public static User createNewFromTo(UserTo newUser) {
       System.out.println("createNewFromTo "+newUser.getId());
        System.out.println("createNewFromTo "+newUser.toString());
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(),newUser.getDownplayWeight(), Role.ROLE_USER);
/*        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(),newUser.getDownplayWeight(), Role.ROLE_USER);*/
    }

    public static UserTo asTo(User user) {
        System.out.println("asTo "+user.getId());
        System.out.println("asTo "+user.toString());
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getNormalWeight());
    }


    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        user.setNormalWeight(userTo.getDownplayWeight());
        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        System.out.println("prepareToSave "+user.getId());
        System.out.println("prepareToSave "+user.toString());
        return user;
    }

}
