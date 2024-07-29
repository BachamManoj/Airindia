package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import com.entity.Flight;


@Stateful
public class FlightManager implements FlightRemote {
    private static final String URL = "jdbc:mysql://localhost:3306/AIRLINES";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR DB PASWORD";
    @Override
    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureTime) {
        List<Flight> flights = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM flights WHERE departure_city = ? AND arrival_city = ? AND departure_time > ? AND status=1";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, departureCity);
            stmt.setString(2, arrivalCity);
            stmt.setTimestamp(3, Timestamp.valueOf(departureTime));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setFlightNumber(rs.getString("flight_number"));
                flight.setDepartureCity(rs.getString("departure_city"));
                flight.setArrivalCity(rs.getString("arrival_city"));
                flight.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
                flight.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
                flight.setAirline(rs.getString("airline"));
                flight.setPrice(rs.getBigDecimal("price"));
                flight.setEconomyseats(rs.getInt("econnomyseats"));
                flight.setBusinessseats(rs.getInt("businessseats"));
                flight.setFirstclassseats(rs.getInt("firstclassseats"));
                flights.add(flight);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }
    @Override
    public String postFlight(Flight flight) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
        	String query = "INSERT INTO flights (flight_number, departure_city, arrival_city, departure_time, arrival_time, airline, price, econnomyseats, businessseats, firstclassseats) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, flight.getFlightNumber());
            stmt.setString(2, flight.getDepartureCity());
            stmt.setString(3, flight.getArrivalCity());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(flight.getDepartureTime()));
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(flight.getArrivalTime()));
            stmt.setString(6, flight.getAirline());
            stmt.setBigDecimal(7, flight.getPrice());
            stmt.setInt(8, flight.getEconomyseats());
            stmt.setInt(9, flight.getBusinessseats());
            stmt.setInt(10, flight.getFirstclassseats());
            stmt.executeUpdate();
            return "Flight data submitted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "While submit some error occured?";
        }
    }
    
    @Override
    public List<Flight> getFlightsOnAdmin() {
        List<Flight> flights = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM flights";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setFlightNumber(rs.getString("flight_number"));
                flight.setDepartureCity(rs.getString("departure_city"));
                flight.setArrivalCity(rs.getString("arrival_city"));
                flight.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
                flight.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
                flight.setAirline(rs.getString("airline"));
                flight.setPrice(rs.getBigDecimal("price"));
                flight.setEconomyseats(rs.getInt("econnomyseats"));
                flight.setBusinessseats(rs.getInt("businessseats"));
                flight.setFirstclassseats(rs.getInt("firstclassseats"));
                flights.add(flight);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }
    
    
    public String updateFlightDepartureTime(String flight_number, LocalDateTime newDepartureTime) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Retrieve the original departure and arrival times
            String selectQuery = "SELECT departure_time, arrival_time FROM flights WHERE flight_number = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
            selectStmt.setString(1, flight_number);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                LocalDateTime originalDepartureTime = rs.getTimestamp("departure_time").toLocalDateTime();
                LocalDateTime originalArrivalTime = rs.getTimestamp("arrival_time").toLocalDateTime();

                // Calculate the duration
                Duration duration = Duration.between(originalDepartureTime, originalArrivalTime);

                // Calculate the new arrival time
                LocalDateTime newArrivalTime = newDepartureTime.plus(duration);

                // Update the departure and arrival times in the database
                String updateQuery = "UPDATE flights SET departure_time = ?, arrival_time = ? WHERE flight_number = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setTimestamp(1, Timestamp.valueOf(newDepartureTime));
                updateStmt.setTimestamp(2, Timestamp.valueOf(newArrivalTime));
                updateStmt.setString(3, flight_number);
                updateStmt.executeUpdate();

                return "Flight departure time updated successfully!";
            } else {
                return "Flight not found!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while updating the flight departure time.";
        }
    }
	@Override
	public List<String> getUsersByFlightNumber(String updateFlightNumber) throws SQLException {
		try (Connection conn = DriverManager.getConnection(url, username, password)){
		List<String> emails = new ArrayList<>();
        String sql = "SELECT DISTINCT email FROM seat WHERE flightNumber = ? AND isFiled=1";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, updateFlightNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    emails.add(rs.getString("email"));
                }
            }
        }
        return emails;
        }
		
	}
    
    
}
