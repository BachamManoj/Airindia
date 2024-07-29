package com.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


import com.model.SeatSelectionRemote;

@ManagedBean(name = "seat",eager = true)
@RequestScoped
public class SeatData {
    private String flightNumber;
    private String email;
    private int seatNumber;
    private String paymentStatus;
    private String response;
    private String phoneNumber;
    private String name;
    private String passportId;
    private Map<Integer, Boolean> seatStatus;
    private String seatClass;
    private int numberOfSeats;
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;
   

    @EJB(lookup = "java:global/Airindia/SeatSelectonManager!com.model.SeatSelectionRemote")
    private SeatSelectionRemote sr;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        flightNumber = (String) fc.getExternalContext().getSessionMap().get("flightNumber");
        email = (String) fc.getExternalContext().getSessionMap().get("email");
        seatClass= (String) fc.getExternalContext().getSessionMap().get("seatClass");
        seatStatus = sr.getSeatStatus(flightNumber,seatClass);
		numberOfSeats = sr.getNumberOfSeats(flightNumber,seatClass );
    }
    
    
    
    
    
    public void bookSeat() {
        try {
        	FacesContext fc = FacesContext.getCurrentInstance();
        	fc.getExternalContext().getSessionMap().put("seatNumber", seatNumber); 
    		fc.getExternalContext().getSessionMap().put("phoneNumber", phoneNumber); 
    		fc.getExternalContext().getSessionMap().put("name", name); 
    		fc.getExternalContext().getSessionMap().put("passportId", passportId); 
    		fc.getExternalContext().getSessionMap().put("cardNumber", cardNumber); 
    		fc.getExternalContext().getSessionMap().put("seatNumber", seatNumber); 
        	fc.getExternalContext().redirect("payment.jsf");
        } catch (Exception e) {
            e.printStackTrace();
            response = "An error occurred while booking the seat.";
        }
    }
  
    // Getters and setters for the properties

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<Integer> getSeatNumbers() {
        List<Integer> seatNumbers = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seatNumbers.add(i);
        }
        return seatNumbers;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SeatSelectionRemote getSr() {
        return sr;
    }

    public void setSr(SeatSelectionRemote sr) {
        this.sr = sr;
    }

    public String getSeatPosition(int seatNumber) {
        int position = (seatNumber - 1) % 6 + 1;
        return String.valueOf(position);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public boolean isSeatOccupied(int seatNumber) {
        return seatStatus != null && seatStatus.getOrDefault(seatNumber, false);
    }

	public Map<Integer, Boolean> getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(Map<Integer, Boolean> seatStatus) {
		this.seatStatus = seatStatus;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }


    
    
}
