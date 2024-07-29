package com.converter;

import java.util.Random;

public class OTPGenerator {
    public static void main(String[] args) {
        int otpLength = 6;
        String otp = generateOTP(otpLength);
        System.out.println("Your OTP is: " + otp);
    }

    public static String generateOTP(int length) {
        String digits = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            otp.append(digits.charAt(random.nextInt(digits.length())));
        }

        return otp.toString();
    }
}
