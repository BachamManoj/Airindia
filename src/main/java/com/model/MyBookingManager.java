package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import com.entity.Mybookings;

@Stateful
public class MyBookingManager implements MyBookingsRemote {
    private static final String URL = "jdbc:mysql://localhost:3306/AIRLINES";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR DB PASWORD";

    @Override
    public List<Mybookings> getBookings(String email) {
        List<Mybookings> bookings = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement("SELECT * FROM seat WHERE email = ? AND isFiled = 1")) {

            psmt.setString(1, email);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Mybookings seat = new Mybookings();
                seat.setSeatid(rs.getInt("seatid"));
                seat.setFlightNumber(rs.getString("flightNumber"));
                seat.setEmail(rs.getString("email"));
                seat.setSeatnumber(rs.getInt("seatNumber"));
                seat.setPaymentstatus(rs.getString("paymentStatus"));
                seat.setIsfiled(rs.getBoolean("isFiled"));
                seat.setPhoneNumber(rs.getString("phoneno"));
                seat.setName(rs.getString("name"));
                seat.setPassportid(rs.getString("passportid"));
                seat.setSeatClass(rs.getString("seatClass"));
                bookings.add(seat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public boolean cancelTicket(int seatid) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(
                 "UPDATE seat SET isFiled = 0 WHERE seatid = ?")) {
            psmt.setInt(1, seatid);
            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0; 
        }
    }

    public List<Mybookings> getCanceledBookings(String email) {
        List<Mybookings> bookings = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement("SELECT * FROM seat WHERE email = ? AND isFiled = 0")) {

            psmt.setString(1, email);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Mybookings seat = new Mybookings();
                seat.setSeatid(rs.getInt("seatid"));
                seat.setFlightNumber(rs.getString("flightNumber"));
                seat.setEmail(rs.getString("email"));
                seat.setSeatnumber(rs.getInt("seatNumber"));
                seat.setPaymentstatus(rs.getString("paymentStatus"));
                seat.setIsfiled(rs.getBoolean("isFiled"));
                seat.setPhoneNumber(rs.getString("phoneno"));
                seat.setName(rs.getString("name"));
                seat.setPassportid(rs.getString("passportid"));
                seat.setSeatClass(rs.getString("seatClass"));
                bookings.add(seat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
