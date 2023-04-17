package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendHtmlEmail(User user, String subject) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject + " " + user.getName());
            helper.setTo(user.getEmail());
            String htmlBody = String.format("""
                   <h1>Bienvenido, %s</h1>
                    """,user.getName());
            helper.setText(htmlBody, true);
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
