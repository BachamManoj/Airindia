package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateful;


import com.entity.Customer;

@Stateful
public class CustomerManager implements CustomerRemote {
    private static final String URL = "jdbc:mysql://localhost:3306/AIRLINES";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR DB PASWORD";
    @Override
    public boolean Create_customer(Customer c) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(
                 "INSERT INTO customers (fullName, mobileNo, email, password, otp) VALUES (?, ?, ?, ?, ?)")) {
            psmt.setString(1, c.getFullName());
            psmt.setString(2, c.getMobileNo());
            psmt.setString(3, c.getEmail());
            psmt.setString(4, c.getPassword());
            psmt.setString(5, c.getOtp());
            psmt.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean Login_customer(String email, String pass) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(
                 "SELECT COUNT(*) FROM customers WHERE email = ? AND password = ?")) {
            psmt.setString(1, email);
            psmt.setString(2, pass);
            try (ResultSet res = psmt.executeQuery()) {
                if (res.next() && res.getInt(1) == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    @Override
    public boolean ValidateOTP(String email, int OTP) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(
                 "SELECT otp FROM customers WHERE email = ?")) {
            psmt.setString(1, email);

            try (ResultSet res = psmt.executeQuery()) {
                if (res.next()) {  
                    int retrievedOtp = res.getInt("otp");
                    if(retrievedOtp == OTP)
                    {
                    	PreparedStatement psmt1 = con.prepareStatement(
               	             "UPDATE customers SET isvalid = 1 WHERE email = ?");
               	       
               	        psmt1.setString(1, email);
               	       
               	        psmt1.executeUpdate();
                    	
                    	return true;
                    }
                    else 
                    {
                    	return false;
                    }
                } 
                else
                {
                    return false;  
                }
            }
        }
    }

    
    @Override
    public boolean ValidateOTP1(String email, int OTP) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(
                 "SELECT otp FROM customers WHERE email = ?")) {
            psmt.setString(1, email);

            try (ResultSet res = psmt.executeQuery()) {
                if (res.next()) {  
                    int retrievedOtp = res.getInt("otp");
                    
                    // Debug: Log retrieved OTP
                    System.out.println("Retrieved OTP from DB: " + retrievedOtp);

                    return retrievedOtp == OTP;
                } else {
                    return false;  
                }
            }
        }
    }

    
    @Override
    public boolean Isvalid(String email) throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(
                 "SELECT isvalid FROM customers WHERE email = ?")) {
            psmt.setString(1, email);

            try (ResultSet res = psmt.executeQuery()) {
                if (res.next()) {  
                    int isValidValue = res.getInt("isvalid");
                    return isValidValue == 1;  
                } else {
                    return false;  
                }
            }
        }
    }

	@Override
	public boolean forgotpasword(String email, int OTP) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement psmt = con.prepareStatement(
	                 "SELECT email FROM customers WHERE email = ?")) {
	            psmt.setString(1, email);

	            try (ResultSet res = psmt.executeQuery()) {
	                if (res.next())
	                {  
	                    String dataemail = res.getString("email");
	                    if(dataemail.equals(email))
	                    {
	                    	PreparedStatement psmt1 = con.prepareStatement("UPDATE customers SET otp = ? WHERE email = ?");
	               	        psmt1.setInt(1, OTP);
	               	        psmt1.setString(2, email);
	               	        psmt1.executeUpdate();
	               	        return true;
	                    }
	                    else {
	                    	return false;
	                    }
	                }
	                else
	                {
	                    return false;  
	                }
	            }
	        }
	}

	@Override
	public boolean passReset(String email, String pass) throws Exception {
	    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement psmt = con.prepareStatement(
	             "UPDATE customers SET password = ? WHERE email = ?")) {
	        psmt.setString(1, pass);
	        System.out.println(pass);
	        psmt.setString(2, email);
	        System.out.println(email);
	        int rowsAffected = psmt.executeUpdate();
	        return rowsAffected > 0; 
	    }
	}

	@Override
	public boolean isAdmincheck(String email) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement psmt = con.prepareStatement(
	                 "SELECT COUNT(*) FROM customers WHERE email = ? AND IsAdminval = 1")) {
	            psmt.setString(1, email);
	            try (ResultSet res = psmt.executeQuery()) {
	                if (res.next() && res.getInt(1) == 1) {
	                    return true;
	                } else {
	                    return false;
	                }
	            }
	        }
		
	}


	
	

	
}
