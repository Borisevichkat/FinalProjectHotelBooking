package by.borisevich.hotel.controller.command.impl;

import by.borisevich.hotel.controller.PageRequest;
import by.borisevich.hotel.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import by.borisevich.hotel.entity.User;

public class LoadCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultPage = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (session.getAttribute("user") != null) {
            resultPage = "/WEB-INF/jsp/main.jsp";
            System.out.println("go to main.jsp");
        } else {
            resultPage = "/WEB-INF/jsp/authorisation.jsp";
            System.out.println("go to authorisation.jsp");
        }
        return resultPage;
    }
}
