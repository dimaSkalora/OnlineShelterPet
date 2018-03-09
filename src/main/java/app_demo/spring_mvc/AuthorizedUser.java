package app_demo.spring_mvc;

import app_demo.spring_mvc.model.AbstractBaseEntity;
import app_demo.spring_mvc.model.User;
import app_demo.spring_mvc.to.UserTo;
import app_demo.spring_mvc.util.UserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        System.out.println( "huigygvtf");
        this.userTo = UserUtil.asTo(user);
        System.out.println("AuthorizedUser "+user.getId());
        System.out.println("AuthorizedUser "+user.getPets());
        System.out.println("AuthorizedUser "+userTo.getId());
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        System.out.println("principal "+auth.getPrincipal());
        System.out.println("auth "+auth.getAuthorities());
        System.out.println("auth.size "+auth.getAuthorities().size());
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        System.out.println("AuthorizedUser get " +user);
        requireNonNull(user, "No authorized user found ");
        return user;
    }

    public static int id() {
        System.out.println("id"+get().userTo.getId());
        return get().userTo.getId();
    }

    public int getId() {
        return userTo.getId();
    }

    public static double getDownplayWeight() {
        return get().userTo.getDownplayWeight();
    }

    public UserTo getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

}
