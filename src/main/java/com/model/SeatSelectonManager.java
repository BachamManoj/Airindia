package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;

import com.entity.Seat;

@Stateful
public class SeatSelectonManager implements SeatSelectionRemote {
    private static final String URL = "jdbc:mysql://localhost:3306/AIRLINES";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR DB PASWORD";

    @Override
    public String selectSeat(String flightNumber, int seatNumber, String email, String passportId, String name, String phoneNumber, String paymentStatus, String seatClass) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement("INSERT INTO seat (flightNumber, email, seatNumber, isFiled, passportid, name, phoneno, paymentStatus, seatClass) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            
            psmt.setString(1, flightNumber);
            psmt.setString(2, email);
            psmt.setInt(3, seatNumber);
            psmt.setBoolean(4, true);
            psmt.setString(5, passportId);
            psmt.setString(6, name);
            psmt.setString(7, phoneNumber);
            psmt.setString(8, paymentStatus);
            psmt.setString(9, seatClass);
            
            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0 ? "success" : "failed";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public Map<Integer, Boolean> getSeatStatus(String flightNumber, String seatClass) {
        Map<Integer, Boolean> seatStatus = new HashMap<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement("SELECT seatNumber, isFiled FROM seat WHERE flightNumber = ? AND seatClass = ?")) {
            
            psmt.setString(1, flightNumber);
            psmt.setString(2, seatClass);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                seatStatus.put(rs.getInt("seatNumber"), rs.getBoolean("isFiled"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seatStatus;
    }
    
    @Override
    public int getNumberOfSeats(String flightNumber, String seatClass) {
        int numberOfSeats = 0;
        String query = "";
        switch (seatClass.toLowerCase()) {
            case "economy":
                query = "SELECT econnomyseats FROM flights WHERE flight_number = ?";
                break;
            case "business":
                query = "SELECT businessseats FROM flights WHERE flight_number = ?";
                break;
            case "firstclass":
                query = "SELECT firstclassseats FROM flights WHERE flight_number = ?";
                break;
        }
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(query)) {
            
            psmt.setString(1, flightNumber);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                numberOfSeats = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfSeats;
    }

    @Override
    public List<Seat> getBookings(String email) {
        List<Seat> bookings = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement("SELECT * FROM seat WHERE email = ?")) {
            
            psmt.setString(1, email);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Seat seat = new Seat();
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
