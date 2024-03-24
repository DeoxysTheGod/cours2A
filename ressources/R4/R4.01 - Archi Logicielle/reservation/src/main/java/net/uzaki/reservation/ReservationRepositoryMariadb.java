package net.uzaki.reservation;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class ReservationRepositoryMariadb implements ReservationRepositoryInterface, Closeable {
    protected Connection dbConnection;

    public ReservationRepositoryMariadb(String infoConnection, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        this.dbConnection = DriverManager.getConnection(infoConnection, user, password);
    }

    @Override
    public void close() {
        try {
            this.dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Reservation getReservation(String reference) {
        Reservation reservation = null;
        try {
            PreparedStatement statement = this.dbConnection.prepareStatement("SELECT * FROM Reservation WHERE id = ?");
            statement.setString(1, reference);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                reservation = new Reservation(result.getString("id"), result.getString("reference"), result.getString("date"));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation;
    }

    public ArrayList<Reservation> getAllReservations() {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        try {
            Statement statement = this.dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Reservation");
            while (result.next()) {
                reservations.add(new Reservation(result.getString("id"), result.getString("reference"), result.getString("date")));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservations;
    }

    @Override
    public boolean registerReservation(String id, String reference) {
        boolean result = false;
        try {
            PreparedStatement statement = this.dbConnection.prepareStatement("INSERT INTO Reservation (id, reference, date) VALUES (?, ?, ?)");
            statement.setString(1, id);
            statement.setString(2, reference);
            statement.setString(3, new java.util.Date().toString());
            result = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean releaseReservation(String reference) {
        boolean result = false;
        try {
            PreparedStatement statement = this.dbConnection.prepareStatement("DELETE FROM Reservation WHERE reference = ?");
            statement.setString(1, reference);
            result = statement.executeUpdate() > 0;
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}
