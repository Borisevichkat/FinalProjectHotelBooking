package by.borisevich.hotel.controller;

import by.borisevich.hotel.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name;
        Command command;
        name = request.getParameter("command");
        // command = provider.takeCommand(name);
        // command.execute(request, response);
    }

}
