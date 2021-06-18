package by.borisevich.hotel.controller;

import by.borisevich.hotel.dao.GuestDAO;
import by.borisevich.hotel.dao.impl.GuestDaoImpl;
import by.borisevich.hotel.entity.Guest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


//@WebServlet("/AddData")
//    public class AddData extends HttpServlet {
//
//        protected void doPost(HttpServletRequest request,
//                              HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        //int columnId = Integer.parseInt(request.getParameter("columnId"));
//        int columnPosition = Integer.parseInt(request.getParameter("columnPosition"));
//        //int rowId = Integer.parseInt(request.getParameter("rowId"));
//        String value = request.getParameter("value");
//        //String columnName = request.getParameter("columnName");
//
//    for(Guest guest: GuestDaoImpl.getGuestsByUserId())
//        {
//            if(guest.getGuestId()==id)
//            {
//                switch (columnPosition)
//                {
//                    case 0:
//                        guest.setPassport(value);
//                        break;
//                    case 1:
//                        guest.setTelephoneNumber(value);
//                        break;
//                    case 2:
//                        guest.setFirstName(value);
//                        break;
//                    case 3:
//                        guest.setLastName(value);
//                        break;
//                    case 4:
//                        guest.setBirthDate(new java.sql.Date(value));
//                        break;
//                    default:
//                        break;
//                }
//                response.getWriter().print(value);
//                return;
//            }
//        }
//    response.getWriter().print("Error - guest cannot be found");
//    }
//
//}
//
//
