package by.borisevich.hotel.dao;

import by.borisevich.hotel.dao.impl.*;

public final class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();

    private final UserDAO userDAO = new UserDAOImpl();
    private final BookingDAO bookingDAO = new BookingDAOImpl();
    private final BookingRoomFundDAO bookingRoomFundDAO = new BookingRoomFundDAOImpl();
    private final InvoiceDAO invoiceDAO = new InvoiceDAOImpl();
    private final RoomFundDAO roomFundDAO = new RoomFundDAOImpl();
    private final GuestDAO guestDAO = new GuestDaoImpl();
    private final GuestBookingRoomFundDao guestBookingRoomFundDao = new GuestBookingRoomFundDAOImpl();

    private DAOProvider() {
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public BookingDAO getBookingDAO() {
        return bookingDAO;
    }

    public BookingRoomFundDAO getBookingRoomFundDAO() {
        return bookingRoomFundDAO;
    }

    public InvoiceDAO getInvoiceDAO() {
        return invoiceDAO;
    }

    public RoomFundDAO getRoomFundDAO() {
        return roomFundDAO;
    }

    public GuestDAO getGuestDAO() {
        return guestDAO;
    }

    public RoomFundDAO roomFundDAO() {
        return roomFundDAO;
    }

}
