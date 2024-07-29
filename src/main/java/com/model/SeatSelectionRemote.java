package com.model;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.entity.Seat;

@Remote
public interface SeatSelectionRemote {
    String selectSeat(String flightNumber, int seatNumber, String email, String passportId, String name, String phoneNumber, String paymentStatus, String seatClass);
    Map<Integer, Boolean> getSeatStatus(String flightNumber, String seatClass);
    int getNumberOfSeats(String flightNumber, String seatClass);
    List<Seat> getBookings(String email);
}
