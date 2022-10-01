package com.projetoemail.projetoemail.service;

import com.projetoemail.projetoemail.dto.EmailDTO;
import com.projetoemail.projetoemail.model.EmailFrom;
import com.projetoemail.projetoemail.repository.EmailFromRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class EmailFromService {

    @Autowired
    private EmailFromRepositoriy emailFromRepositoriy;

    public List<EmailFrom> getAll() {
        return emailFromRepositoriy.findAll();
    }

    public Optional<EmailFrom> getId(Long Id) {
        return emailFromRepositoriy.findById(Id);
    }

    public EmailFrom create(EmailFrom emailFrom){
        return emailFromRepositoriy.save(emailFrom);
    }

    public EmailFrom getByEmail(String email){
        return emailFromRepositoriy.findByEmail(email);
    }
}
