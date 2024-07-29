package com.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.converter.OTPGenerator;
import com.entity.Customer;
import com.model.CustomerRemote;

@ManagedBean(name = "customer", eager = true)
public class CustomerData {
    

    String fullName;
    String mobileNo;
    String email;
    String password;
    int otp;
    boolean response;
    String validate;

    @EJB(lookup = "java:global/Airindia/CustomerManager!com.model.CustomerRemote")
    CustomerRemote cr;

    @EJB
    private EmailService emailService;

    public void login() {
        Customer c = new Customer();
        c.setEmail(email);
        c.setPassword(password);
        try {
            if (cr.Login_customer(email, password))
            {
            	if(cr.isAdmincheck(email))
            	{
            		FacesContext fc = FacesContext.getCurrentInstance();
                    fc.getExternalContext().getSessionMap().put("email", email);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("admin.jsf?email=" + email);
            	}
            	else if (cr.Isvalid(email)) 
                {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.getExternalContext().getSessionMap().put("email", email);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("resources/pages/client/clientdashboard.jsf?email=" + email);
                } 
                else 
                {
                    validate = "Validate your account to login by otp";
                }
            } 
            else 
            {
                validate = "WRONG PASSWORD";
            }
        } catch (Exception e) {
            
        }
    }

    public void validateotp() throws Exception {
        // Retrieve email from session
    	FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("email", email);
        // Debug: Log email and OTP
        System.out.println("Validating OTP for email: " + email + " with OTP: " + otp);

        // Validate OTP
        if (cr.ValidateOTP(email, otp)) {
            // Redirect on successful OTP validation
            FacesContext.getCurrentInstance().getExternalContext().redirect("Successpage.jsf");
        } else {
            // Set validation message on incorrect OTP
            validate = "Incorrect OTP";
        }
    }


    public void forgotpas() throws Exception {
        Customer c = new Customer();
        String backendOTP = OTPGenerator.generateOTP(6);
        c.setOtp(backendOTP);
        String to = email;
        String subject = "FORGOT PASSWORD OTP BY AIRLINES";
        String body = "Dear " + fullName + ", you requested to change your password. This is the otp to change the password: " + backendOTP + ".";
        int otp1 = Integer.parseInt(backendOTP);
        if (cr.forgotpasword(email, otp1)) {
            emailService.sendEmail(to, subject, body);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().getSessionMap().put("email", email);
            FacesContext.getCurrentInstance().getExternalContext().redirect("otpvalidatorfp.jsf?email=" + email);
            validate = "OTP SENT TO YOUR MAIL";
        } else {
            validate = "THE EMAIL IS NOT REGISTERED YET!!";
        }
    }

    public void passreset() throws Exception {
        email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
       
        if (cr.passReset(email, password)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("resetSuccess.jsf");
        } else {
            validate = "ERROR OCCURRED !!!";
        }
    }

    public void validForgotOtp() throws Exception {
        if (cr.ValidateOTP(email, otp)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().getSessionMap().put("email", email);
            FacesContext.getCurrentInstance().getExternalContext().redirect("passwordReset.jsf?email=" + email);
        } else {
            validate = "incorrect otp";
        }
    }

    public void register() {
        Customer c = new Customer();
        c.setFullName(fullName);
        c.setEmail(email);
        c.setPassword(password);
        c.setMobileNo(mobileNo);
        String backendOTP = OTPGenerator.generateOTP(6);
        c.setOtp(backendOTP);
        String to = email;
        String subject = "OTP BY AIRLINES";
        String body = "Dear " + fullName + ", thanks for signing up in our AIRLINE for booking your flights. To start your journey with us, please use the below otp to activate your account: " + backendOTP;
        try {
            emailService.sendEmail(to, subject, body);
            response = cr.Create_customer(c);
        } catch (Exception e) {
            
        }
    }

    // Getters and Setters

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public CustomerRemote getCr() {
        return cr;
    }

    public void setCr(CustomerRemote cr) {
        this.cr = cr;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }
}
