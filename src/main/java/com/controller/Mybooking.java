package com.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import com.entity.Mybookings;
import com.model.MyBookingsRemote;

@ManagedBean(name = "mybookingsbean", eager = true)
@RequestScoped
public class Mybooking implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private List<Mybookings> bookings;
    private List<Mybookings> canceledBookings;
    private int seatid;

    @EJB(lookup = "java:global/Airindia/MyBookingManager!com.model.MyBookingsRemote")
    private MyBookingsRemote br;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        email = (String) fc.getExternalContext().getSessionMap().get("email");
        if (email != null && !email.isEmpty()) {
            bookings = br.getBookings(email);
            canceledBookings = br.getCanceledBookings(email);
        } else {
            System.out.println("Email is not set in the session");
        }
    }

    public void cancelTicket() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String seatidParam = fc.getExternalContext().getRequestParameterMap().get("seatid");
        if (seatidParam != null) {
            seatid = Integer.parseInt(seatidParam);
            System.out.println("seat id value is:" + seatid);
            try {
                if (br.cancelTicket(seatid)) {
                    System.out.println("canceled");
                    bookings = br.getBookings(email);
                    canceledBookings = br.getCanceledBookings(email);
                } else {
                    System.out.println("error!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("seatid parameter is missing");
        }
    }

    public List<Mybookings> getBookings() {
        return bookings;
    }

    public void setBookings(List<Mybookings> bookings) {
        this.bookings = bookings;
    }

    public List<Mybookings> getCanceledBookings() {
        return canceledBookings;
    }

    public void setCanceledBookings(List<Mybookings> canceledBookings) {
        this.canceledBookings = canceledBookings;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSeatid() {
        return seatid;
    }

    public void setSeatid(int seatid) {
        this.seatid = seatid;
    }
}
