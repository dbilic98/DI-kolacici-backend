package com.dikolacici.DI_kolacici.controller;


import com.dikolacici.DI_kolacici.domain.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam  String htmlBody) {
        emailService.sendMail(to, subject, htmlBody);
        return "Email send successful";
    }
}
