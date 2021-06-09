package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.Invoice;

import java.util.List;

public interface InvoiceDAO {

    List<Invoice> getInvoiceByUser(int userId);

    List<Invoice> getInvoiceByBooking(int bookingId);

    void createInvioce(Invoice invoice);

    void updateInvioce(Invoice invoice);
}
