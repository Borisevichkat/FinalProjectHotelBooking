package by.borisevich.hotel.dao;

import by.borisevich.hotel.connectionPool.ConnectionPoolException;
import by.borisevich.hotel.entity.Invoice;

import java.util.List;

public interface InvoiceDAO {

    List<Invoice> getInvoiceByUser(int userId)  throws DAOException;

    List<Invoice> getInvoiceByBooking(int bookingId)  throws DAOException;

    void createInvioce(Invoice invoice) throws DAOException;

    void updateInvioce(Invoice invoice)  throws DAOException;
}
