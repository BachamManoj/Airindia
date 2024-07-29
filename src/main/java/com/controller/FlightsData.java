package com.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.entity.Flight;
import com.model.FlightRemote;

@ManagedBean(name = "flights", eager = true)
@ViewScoped
public class FlightsData {

    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String airline;
    private BigDecimal price;
    private List<Flight> searchResults;
    private String response;
    private int economyseats;
    private int businessseats;
    private int firstclassseats;
    private String type;
    private String seatClass;
    private String updateFlightNumber;
    private LocalDateTime newDepartureTime;

    @EJB(lookup = "java:global/Airindia/FlightManager!com.model.FlightRemote")
    private FlightRemote fr;
    
    @EJB
    private EmailService emailService;

    public void postflight() {
        Flight flightIns = new Flight();
        flightIns.setFlightNumber(flightNumber);
        flightIns.setDepartureCity(departureCity);
        flightIns.setArrivalCity(arrivalCity);
        flightIns.setDepartureTime(departureTime);
        flightIns.setArrivalTime(arrivalTime);
        flightIns.setAirline(airline);
        flightIns.setPrice(price);
        flightIns.setBusinessseats(businessseats);
        flightIns.setEconomyseats(economyseats);
        flightIns.setFirstclassseats(firstclassseats);
        response = fr.postFlight(flightIns);
        resetFields();
    }
    
    public void resetFields() {
        flightNumber = null;
        departureCity = null;
        arrivalCity = null;
        departureTime = null;
        arrivalTime = null;
        airline = null;
        price = null;
        economyseats = 0;
        businessseats = 0;
        firstclassseats = 0;
    }
    
    public void seatSelection() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        String flightNumber = fc.getExternalContext().getRequestParameterMap().get("flightNumber");
        fc.getExternalContext().getSessionMap().put("flightNumber", flightNumber); 
        fc.getExternalContext().getSessionMap().put("seatClass", seatClass);
        fc.getExternalContext().redirect("seatselection.jsf");
    }

    public void updateFlightDepartureTime() throws SQLException {
    	List<String> emails = fr.getUsersByFlightNumber(updateFlightNumber); // Fetch users who booked the flight

        if (emails != null && !emails.isEmpty()) {
            String subject = "Flight Departure Time Update";
            String body = "Dear Customer, \n\nThe departure time for your flight with flight number " + updateFlightNumber +
                          " has been updated. The new departure time is " + newDepartureTime + ".\n\nThank you for flying with us.";
            
            for (String email : emails) {
                emailService.sendEmail(email, subject, body);
            }
        }
        response = fr.updateFlightDepartureTime(updateFlightNumber, newDepartureTime);
    
    }

    public String getFlightNumber() {
    	return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("flightNumber");
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public FlightRemote getFr() {
        return fr;
    }

    public void setFr(FlightRemote fr) {
        this.fr = fr;
    }

    public void searchFlights() {
        searchResults = fr.searchFlights(departureCity, arrivalCity, departureTime);
    }

    public List<Flight> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Flight> searchResults) {
        this.searchResults = searchResults;
    }

	public int getEconomyseats() {
		return economyseats;
	}

	public void setEconomyseats(int economyseats) {
		this.economyseats = economyseats;
	}

	public int getBusinessseats() {
		return businessseats;
	}

	public void setBusinessseats(int businessseats) {
		this.businessseats = businessseats;
	}

	public int getFirstclassseats() {
		return firstclassseats;
	}

	public void setFirstclassseats(int firstclassseats) {
		this.firstclassseats = firstclassseats;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

    public String getUpdateFlightNumber() {
        return updateFlightNumber;
    }

    public void setUpdateFlightNumber(String updateFlightNumber) {
        this.updateFlightNumber = updateFlightNumber;
    }

    public LocalDateTime getNewDepartureTime() {
        return newDepartureTime;
    }

    public void setNewDepartureTime(LocalDateTime newDepartureTime) {
        this.newDepartureTime = newDepartureTime;
    }
}
