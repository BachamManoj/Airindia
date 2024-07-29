package com.model;

import java.util.List;
import javax.ejb.Remote;
import com.entity.Mybookings;

@Remote
public interface MyBookingsRemote {
    public List<Mybookings> getBookings(String email);
    public boolean cancelTicket(int seatid) throws Exception;
    public List<Mybookings> getCanceledBookings(String email);
}
