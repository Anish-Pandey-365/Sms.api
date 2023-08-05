package com.twilio.sms.api.payload;

public class SmsRequest {

    private String toNum;
    private String messageBody;

    public SmsRequest() {
    }

    public SmsRequest(String toNum, String messageBody) {
        this.toNum = toNum;
        this.messageBody = messageBody;
    }

    public String getToNum() {
        return toNum;
    }

    public void setToNum(String toNum) {
        this.toNum = toNum;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessage(String messageBody) {
        this.messageBody = messageBody;
    }
}

