package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateful;

@Stateful
public class CustomerComplaintManager implements CustomerComplaintRemote {
    private static final String URL = "jdbc:mysql://localhost:3306/AIRLINES";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR DB PASWORD";

    @Override
    public void postComplaint(String email, String complaint) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO CustomerComplaints (email, complaint) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, complaint);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void replyComplaint(String email, String reply) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE CustomerComplaints SET reply = ? WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, reply);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
