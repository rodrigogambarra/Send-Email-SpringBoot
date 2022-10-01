package com.projetoemail.projetoemail.service;

import com.projetoemail.projetoemail.model.EmailFrom;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class EmailSenderService {

    public JavaMailSender getJavaMailSender(EmailFrom emailFrom) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);

        mailSender.setUsername(emailFrom.getEmail());
        mailSender.setPassword(emailFrom.getSenha());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.test-connection", "false");

        return mailSender;
    }
}
