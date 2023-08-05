package com.twilio.sms.api.controller;

import com.twilio.sms.api.payload.OTPVerificationRequest;
import com.twilio.sms.api.payload.PhoneNumberRequest;
import com.twilio.sms.api.service.OTPVerificationService;
import com.twilio.sms.api.service.TwilioSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OTPController {

    private final TwilioSmsService twilioSmsService;
    private final OTPVerificationService otpVerificationService;

    @Autowired
    public OTPController(TwilioSmsService twilioSmsService, OTPVerificationService otpVerificationService) {
        this.twilioSmsService = twilioSmsService;
        this.otpVerificationService = otpVerificationService;
    }

    @PostMapping("/generate-otp")
    public void generateOTP(@RequestBody PhoneNumberRequest request) {
        String phoneNumber = request.getPhoneNumber();
        String otp = otpVerificationService.generateOTP(phoneNumber);
        twilioSmsService.sendSMS(phoneNumber, "Your OTP is: " + otp);
    }
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestBody OTPVerificationRequest request) {
        String phoneNum = request.getPhoneNum();
        String otp = request.getOtp();

        boolean isOTPValid = otpVerificationService.verifyOTP(phoneNum, otp);

        if (isOTPValid) {
            return ResponseEntity.ok("OTP verification successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
    }
}
