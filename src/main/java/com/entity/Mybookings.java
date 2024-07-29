package com.entity;

import java.io.Serializable;

public class Mybookings implements Serializable {
    private static final long serialVersionUID = 1L;

    private String flightNumber;
    private String email;
    private int seatnumber;
    private String paymentstatus;
    private boolean isfiled;
    private String phoneNumber;
    private String name;
    private String passportid;
    private String seatClass;
    private int seatid;

    // Getters and Setters
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

    public int getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public boolean isIsfiled() {
        return isfiled;
    }

    public void setIsfiled(boolean isfiled) {
        this.isfiled = isfiled;
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

    public String getPassportid() {
        return passportid;
    }

    public void setPassportid(String passportid) {
        this.passportid = passportid;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }



	public int getSeatid() {
		return seatid;
	}

	public void setSeatid(int seatid) {
		this.seatid = seatid;
	}
}
