package com.online.shelter.pet.servlet.web;

import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.web.pet.PetRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static com.online.shelter.pet.servlet.util.DateTimeUtil.parseLocalDate;
import static com.online.shelter.pet.servlet.util.DateTimeUtil.parseLocalTime;

public class PetServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private PetRestController petController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        petController = springContext.getBean(PetRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if(action == null) {
            Pet pet = new Pet(LocalDateTime.parse(request.getParameter("createDate")),
                    request.getParameter("typePet"), request.getParameter("namePet"), request.getParameter("breed"),
                    request.getParameter("sex"), request.getParameter("color"), Double.parseDouble(request.getParameter("age")),
                    Integer.parseInt(request.getParameter("growth")), Double.parseDouble(request.getParameter("weight")),
                    request.getParameter("namePerson"), request.getParameter("phone"), request.getParameter("email"));

            if (request.getParameter("id").isEmpty()) {
                petController.create(pet);
            } else {
                petController.update(pet, getId(request));
            }
            response.sendRedirect("pets");
        }else  if("filter".equals(action)){
            LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
            LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
            LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
            LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
            request.setAttribute("pets", petController.getBetween(startDate, startTime, endDate, endTime));
            request.getRequestDispatcher("/pets.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action){
            case "delete":
                int id = getId(request);
                petController.delete(id);
                response.sendRedirect("pets");
            case "create":
            case "update":
                final Pet pet = "create".equals(action) ?
                        new Pet(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),"","","","","",0,0,0,"","","") :
                        petController.get(getId(request));
                request.setAttribute("pet",pet);
                request.getRequestDispatcher("/petForm.jsp").forward(request,response);
                break;
            case "all":
            default:
                request.setAttribute("pets", petController.getAll());
                request.getRequestDispatcher("/pets.jsp").forward(request,response);
                break;
        }
    }

    private int getId(HttpServletRequest request){
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
