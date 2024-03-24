package net.uzaki.user;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class UserRepositoryMariadb implements UserRepositoryInterface, Closeable {
    protected Connection dbConnection;

    public UserRepositoryMariadb(String infoConnection, String user, String password) throws ClassNotFoundException, SQLException {
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

    public User getUser(String id) {
        User user = null;
        try {
            PreparedStatement statement = this.dbConnection.prepareStatement("SELECT * FROM User WHERE id = ?");
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new User(result.getString("id"), result.getString("name"), result.getString("pwd"), result.getString("mail"));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            Statement statement = this.dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM User");
            while (result.next()) {
                users.add(new User(result.getString("id"), result.getString("name"), result.getString("pwd"), result.getString("mail")));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users;
    }

}
