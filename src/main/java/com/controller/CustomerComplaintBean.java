package com.controller;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.model.CustomerComplaintRemote;

@ManagedBean
@RequestScoped
public class CustomerComplaintBean {

    @EJB
    private CustomerComplaintRemote complaintManager;

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

    // Methods for posting and replying to complaints
    public void postComplaint() {
        complaintManager.postComplaint(email, complaint);
    }

    public void replyComplaint() {
        complaintManager.replyComplaint(email, reply);
    }
}
