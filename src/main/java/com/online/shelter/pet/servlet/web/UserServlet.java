package com.online.shelter.pet.servlet.web;

import com.online.shelter.pet.servlet.AuthorizedUser;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private static final Logger logger = getLogger(UserServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        response.sendRedirect("pets");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("redirect to users");

       // req.getRequestDispatcher("/users.jsp").forward(req, resp);
        resp.sendRedirect("users.jsp");
    }
}
