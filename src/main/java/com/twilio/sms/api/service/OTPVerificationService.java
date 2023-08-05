package com.twilio.sms.api.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPVerificationService {

    private final Map<String, String> otpMap = new HashMap<>();

    public String generateOTP(String phoneNumber) {
        Random random = new Random();
        String otp = String.format("%04d", random.nextInt(10000));
        otpMap.put(phoneNumber, otp);
        return otp;
    }

    public boolean verifyOTP(String phoneNum, String otp) {
        String storedOTP = otpMap.get(phoneNum);
        if (storedOTP != null && storedOTP.equals(otp)) {
            otpMap.remove(phoneNum); // Remove the OTP entry after verification
            return true;
        }
        return false;
    }
}

