package by.borisevich.hotel.controller.command.impl;

import by.borisevich.hotel.controller.PageRequest;
import by.borisevich.hotel.controller.command.Command;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.entity.User;
import by.borisevich.hotel.service.ServiceProvider;
import by.borisevich.hotel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorisationCommand implements Command {

    ServiceProvider serviceProvider = ServiceProvider.getInstance();
    UserService userService = serviceProvider.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String resultPage = null;
            HttpSession session = request.getSession();
            session.setAttribute("user", false);
            session.setAttribute("userLoginError", false);
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            try {
                User user = userService.authorisation(email, password);
                if (user != null) {
                    session.setAttribute("user", user);
                    System.out.println("YEEEES");
                    resultPage = PageRequest.MAIN_PAGE;
                } else {
                    session.setAttribute("userLoginError", true);
                    resultPage = PageRequest.GO_TO_LOGIN_PAGE;
                }

            } catch (DAOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return resultPage;
        }
    }

