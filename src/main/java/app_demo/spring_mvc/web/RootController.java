package app_demo.spring_mvc.web;

import app_demo.spring_mvc.AuthorizedUser;
import app_demo.spring_mvc.to.UserTo;
import app_demo.spring_mvc.util.UserUtil;
import app_demo.spring_mvc.web.user.AbstractUserController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

import static app_demo.spring_mvc.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL;

@Controller
public class RootController extends AbstractUserController{

    @GetMapping("/")
    public String root() {
        return "redirect:petsAllUsers";
    }

    //    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/pets")
    public String pets() {
        return "pets";
    }

    @GetMapping("/petsAllUsers")
    public String petsAllUsers() {
       return "petsAllUsers";
    }

    @GetMapping("/profile")
    public String profile(ModelMap model, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        model.addAttribute("userTo", authorizedUser.getUserTo());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        if (result.hasErrors()) {
            return "profile";
        }
        try {
            super.update(userTo, authorizedUser.getId());
            authorizedUser.update(userTo);
            status.setComplete();
            return "redirect:petsAllUsers";
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            return "profile";
        }
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        }
        try {
            super.create(UserUtil.createNewFromTo(userTo));
            status.setComplete();
            return "redirect:login?message=app.registered&username=" + userTo.getEmail();
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            model.addAttribute("register", true);
            return "profile";
        }
    }
}
