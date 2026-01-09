package com.grievance.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String message) {
        // EMAIL DISABLED FOR NOW
        System.out.println("Email skipped: " + subject);
    }
}
