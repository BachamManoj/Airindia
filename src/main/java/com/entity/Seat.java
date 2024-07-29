package com.entity;

import java.io.Serializable;

public class Seat implements Serializable{


	private static final long serialVersionUID = 1L;
	
	String flightNumber;
	String email;
	int seatnumber;
	String paymentstatus;
	boolean isfiled;
	String phoneNumber;
    String name;
    String passportid;
    String seatClass;
   
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
	
}
