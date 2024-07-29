package com.model;



import javax.ejb.Remote;

import com.entity.Customer;

@Remote
public interface CustomerRemote {
    public boolean Create_customer(Customer c)throws Exception;
    public boolean Login_customer(String email,String pass)throws Exception;
    public boolean ValidateOTP(String email,int OTP)throws Exception;
    public boolean Isvalid(String email)throws Exception;
    public boolean forgotpasword(String email,int OTP)throws Exception;
    public boolean passReset(String email,String pass)throws Exception;
    public boolean ValidateOTP1(String email, int OTP) throws Exception ;
    public boolean isAdmincheck(String email) throws Exception;
    
}
