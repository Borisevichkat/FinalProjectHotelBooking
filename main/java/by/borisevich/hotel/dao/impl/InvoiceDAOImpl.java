package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.connectionPool.ConnectionPool;
import by.borisevich.hotel.connectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.dao.InvoiceDAO;
import by.borisevich.hotel.entity.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {

    private static final String query = "INSERT INTO Invoice (totalCost, status, booking, userId)"
            + " VALUES (?, ?, ?, ?)";
    private static final String queryGetInvoiceByUserId = "SELECT id, totalCost, status, booking  FROM Invoice WHERE userId = ?";
    private static final String queryGetInvoiceByBookingId = "SELECT id, totalCost, status, userId  FROM Invoice WHERE bookingId = ?";
    private static final String QUERY_UPDATE_INVOICE ="UPDATE Invoice SET totalCost = ?, status = ?, booking = ?, user = ? WHERE id=?";

    @Override
    public ArrayList<Invoice> getInvoiceByUser(int userId) throws DAOException{

        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<Invoice> invoices = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(queryGetInvoiceByUserId);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                invoices.add(new Invoice(resultSet));
            }
        } catch(ConnectionPoolException e){
                throw new DAOException(e);
        } catch(SQLException e){
                System.out.println(e.getMessage());
        } finally{
                pool.closeConnection(connection, ps);
        }
        return invoices;
    }


    @Override
    public List<Invoice> getInvoiceByBooking(int bookingId) throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<Invoice> invoices = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(queryGetInvoiceByBookingId);
            ps.setInt(1, bookingId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                invoices.add(new Invoice(resultSet));
            }
        } catch(ConnectionPoolException e){
            throw new DAOException(e);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally{
            pool.closeConnection(connection, ps);
        }
        return invoices;
    }

    @Override
    public void createInvioce(Invoice invoice) throws DAOException {

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
        }
            catch (ConnectionPoolException e) {
                throw new DAOException(e);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                pool.closeConnection(connection, ps);
            }
        }

    @Override
    public void updateInvioce(Invoice invoice) throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_UPDATE_INVOICE);
            ps.setDouble(1, invoice.getTotalCost());
            ps.setString(2, invoice.getStatus());
            ps.setInt(3, invoice.getBooking());
            ps.setInt(4, invoice.getUser());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating invoice failed, no rows affected.");
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            pool.closeConnection(connection, ps);
        }
    }
}
