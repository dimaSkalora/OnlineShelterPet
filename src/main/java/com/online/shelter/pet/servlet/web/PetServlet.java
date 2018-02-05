package com.online.shelter.pet.servlet.web;

import com.online.shelter.pet.servlet.AuthorizedUser;
import com.online.shelter.pet.servlet.model.Pet;
import com.online.shelter.pet.servlet.repository.mock.InMemoryPetRepositoryImpl;
import com.online.shelter.pet.servlet.repository.PetRepository;
import com.online.shelter.pet.servlet.util.PetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class PetServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(PetServlet.class);

    private PetRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryPetRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Pet pet = new Pet(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDate.parse(req.getParameter("createDate")),
                req.getParameter("typePet"),req.getParameter("namePet"), req.getParameter("breed"),
                req.getParameter("sex"), req.getParameter("color"), Double.parseDouble(req.getParameter("age")),
                Integer.parseInt(req.getParameter("growth")), Double.parseDouble(req.getParameter("weight")),
                req.getParameter("namePerson"),req.getParameter("phone"),req.getParameter("email"));

        logger.info(pet.isNew() ? "Create{}" : "Update{}",pet);
        repository.save(pet, AuthorizedUser.id());
        resp.sendRedirect("pets");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action == null ? "all" :action){
            case "delete":
                int id = getId(req);
                logger.info("Delete {}",id);
                repository.delete(id,AuthorizedUser.id());
                resp.sendRedirect("pets");
            case "create":
            case "update":
                final Pet pet = "create".equals(action)?
                        new Pet(LocalDate.now(),"","","","","",0,0,0,"","","") :
                        repository.get(getId(req),AuthorizedUser.id());
                req.setAttribute("pet",pet);
                req.getRequestDispatcher("/petForm.jsp").forward(req,resp);
                break;
            case "all":
            default:
                logger.info("getAll");
                req.setAttribute("pets",
                        PetUtil.getWithDownplayWeight(repository.getAll(AuthorizedUser.id()),PetUtil.DEFAULT_NOLMAL_WEIGHT));
                req.getRequestDispatcher("/pets.jsp").forward(req,resp);
        }
    }

    private int getId(HttpServletRequest request){
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
