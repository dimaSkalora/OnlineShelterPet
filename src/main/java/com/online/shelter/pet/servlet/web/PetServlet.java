package com.online.shelter.pet.servlet.web;

import com.online.shelter.pet.servlet.util.PetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(PetServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("getAll");
        req.setAttribute("pets", PetUtil.getWithDownplayWeight(PetUtil.PETS, PetUtil.DEFAULT_NOLMAL_WEIGHT));
        req.getRequestDispatcher("/pets.jsp").forward(req, resp);
      /*  RequestDispatcher view = req.getRequestDispatcher("/pets.jsp");
        view.forward(req, resp);*/
    }
}
