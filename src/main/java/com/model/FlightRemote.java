package com.model;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Remote;
import com.entity.Flight;


@Remote
public interface FlightRemote {
	public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureTime);
    public String postFlight(Flight f);
    public List<Flight> getFlightsOnAdmin();
    public String updateFlightDepartureTime(String flight_number, LocalDateTime newDepartureTime);
	public List<String> getUsersByFlightNumber(String updateFlightNumber) throws SQLException;
}
