package com.model;

import javax.ejb.Remote;

@Remote
public interface CustomerComplaintRemote {
    void postComplaint(String email, String complaint);
    void replyComplaint(String email, String reply);
}
