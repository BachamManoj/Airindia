package com.entity;

import java.io.Serializable;

public class CustomerComplaint implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String email;
    private String complaint;
    private String reply;

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
