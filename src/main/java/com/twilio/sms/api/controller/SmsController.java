package com.twilio.sms.api.controller;

import com.twilio.sms.api.payload.SmsRequest;
import com.twilio.sms.api.service.TwilioSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SmsController {

    private final TwilioSmsService twilioSmsService;

    @Autowired
    public SmsController(TwilioSmsService twilioSmsService) {
        this.twilioSmsService = twilioSmsService;
    }

   // http://localhost:8080/send-sms
    @PostMapping("/send-sms")
    public void sendSMS(@RequestBody SmsRequest request) {
        twilioSmsService.sendSMS(request.getToNum(), request.getMessageBody());
    }

}
