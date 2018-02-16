package com.online.shelter.pet.spring_mvc.web.pet;

import com.online.shelter.pet.spring_mvc.model.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static com.online.shelter.pet.spring_mvc.util.DateTimeUtil.parseLocalDate;
import static com.online.shelter.pet.spring_mvc.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/pets")
public class JspPetController extends AbstractPetController {

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/pets";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("pet", super.get(getId(request)));
        return "petForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pet", new Pet(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "","","","","",0,0,0,"","",""));
        return "petForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Pet pet = new Pet(LocalDateTime.parse(request.getParameter("createDate")),
                request.getParameter("typePet"), request.getParameter("namePet"), request.getParameter("breed"),
                request.getParameter("sex"), request.getParameter("color"), Double.parseDouble(request.getParameter("age")),
                Integer.parseInt(request.getParameter("growth")), Double.parseDouble(request.getParameter("weight")),
                request.getParameter("namePerson"), request.getParameter("phone"), request.getParameter("email"));

        if (request.getParameter("id").isEmpty()) {
            super.create(pet);
        } else {
            super.update(pet, getId(request));
        }
        return "redirect:/pets";
    }

    @PostMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("pets", super.getBetween(startDate, startTime, endDate, endTime));
        return "pets";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
