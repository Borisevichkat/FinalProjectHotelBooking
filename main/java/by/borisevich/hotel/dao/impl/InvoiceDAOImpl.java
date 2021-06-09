package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.ConnectionPool.ConnectionPool;
import by.borisevich.hotel.ConnectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.InvoiceDAO;
import by.borisevich.hotel.entity.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {

    private static final String query = "INSERT INTO Invoice (totalCost, status, booking, userId)"
            + " VALUES (?, ?, ?, ?)";
    private static final String queryGetInvoiceByUserId = "SELECT id, totalCost, status, booking  FROM Invoice WHERE userId = ?";


    @Override
    public ArrayList<Invoice> getInvoiceByUser(int userId) {

        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<Invoice> invoices = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(queryGetInvoiceByUserId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                invoices.add(new Invoice(resultSet));
            }
            pool.closeConnection(connection, ps);
        } catch (ConnectionPoolException e) {
            //
        } catch (SQLException e) {
            //
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                //
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                //
            }
        }
        return new ArrayList<Invoice>();
    }


    @Override
    public List<Invoice> getInvoiceByBooking(int bookingId) {
        return null;
    }

    @Override
    public void createInvioce(Invoice invoice) {

        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, invoice.getTotalCost());
            ps.setString(2, invoice.getStatus());
            ps.setInt(3, invoice.getBooking());
            ps.setInt(4, invoice.getUser());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating invoice failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    invoice.setInvoiceId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating invoice failed, no ID obtained.");
                }
            }

            pool.closeConnection(connection, ps);
        } catch (ConnectionPoolException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                //
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                //
            }
        }
    }

    @Override
    public void updateInvioce(Invoice invoice) {

    }
}
