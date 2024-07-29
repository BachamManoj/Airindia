package com.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.model.SeatSelectionRemote;

import java.util.Map;
import java.util.UUID;

@ManagedBean(name = "payment", eager = true)
public class PaymentBean {

    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;
    private String paymentId;
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
    
    @EJB(lookup = "java:global/Airindia/SeatSelectonManager!com.model.SeatSelectionRemote")
    private SeatSelectionRemote sr;
    
    @EJB
    private EmailService emailService;
    
    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        flightNumber = (String) fc.getExternalContext().getSessionMap().get("flightNumber");
        email = (String) fc.getExternalContext().getSessionMap().get("email");
        seatClass= (String) fc.getExternalContext().getSessionMap().get("seatClass");
        seatNumber=(int) fc.getExternalContext().getSessionMap().get("seatNumber");
        passportId=(String) fc.getExternalContext().getSessionMap().get("passportId");;
        name=(String) fc.getExternalContext().getSessionMap().get("name");;
        phoneNumber=(String) fc.getExternalContext().getSessionMap().get("phoneNumber");;
    }

    
    public String processPayment() {
        
        this.paymentStatus = UUID.randomUUID().toString();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Payment successful! Payment ID: " + this.paymentStatus, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        response = sr.selectSeat(flightNumber, seatNumber, email, passportId, name, phoneNumber, paymentStatus, seatClass);
        
        String to = email;
        String subject = "SEAT BOOK SUCESSFULLY🧳✈️";
        String body = "<html>" +
                "<head><style>" +
                "table { width: 100%; border-collapse: collapse; }" +
                "th, td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }" +
                "th { background-color: #f2f2f2; }" +
                "tr:hover { background-color: #f5f5f5; }" +
                "</style></head>" +
                "<body>" +
                "<h2>Booking Confirmation</h2>" +
                "<p>Dear Customer,</p>" +
                "<p>Your seat has been successfully booked. Here are the details:</p>" +
                "<table>" +
                "<tr><th>Flight Number</th><td>" + flightNumber + "</td></tr>" +
                "<tr><th>Seat Number</th><td>" + seatNumber + "</td></tr>" +
                "<tr><th>Seat Class</th><td>" + seatClass + "</td></tr>" +
                "<tr><th>Name</th><td>" + name + "</td></tr>" +
                "<tr><th>Phone Number</th><td>" + phoneNumber + "</td></tr>" +
                "<tr><th>Passport ID</th><td>" + passportId + "</td></tr>" +
                "<tr><th>Payment Status</th><td>" + paymentStatus + "</td></tr>" +
                "</table>" +
                "<p>Thank you for booking with us!</p>" +
                "</body></html>";
        emailService.sendEmail(to, subject, body);
        return null; 
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

    public String getPaymentId() {
        return paymentId;
    }


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


	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
}
