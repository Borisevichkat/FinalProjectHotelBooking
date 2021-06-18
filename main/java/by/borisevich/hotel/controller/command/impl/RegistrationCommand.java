package by.borisevich.hotel.controller.command.impl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.borisevich.hotel.controller.PageRequest;
import by.borisevich.hotel.controller.command.Command;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.entity.User;
import by.borisevich.hotel.service.impl.UserServiceImpl;

public class RegistrationCommand implements Command {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            try {
                User userModel = new User(firstName, lastName, email, password, false);
                UserServiceImpl userService = new UserServiceImpl();
                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(email);
                System.out.println(password);
                userService.registration(userModel);
                System.out.println("3");
            } catch (DAOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            response.sendRedirect("index.jsp");

        }
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User userModel = new User(firstName, lastName, email, password, false);
            UserServiceImpl userService = new UserServiceImpl();
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(email);
            System.out.println(password);
            userService.registration(userModel);
            System.out.println("3");
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return PageRequest.MAIN_PAGE;
    }

}
