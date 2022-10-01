package com.projetoemail.projetoemail.service;

import com.projetoemail.projetoemail.dto.EmailDTO;
import com.projetoemail.projetoemail.model.Email;
import com.projetoemail.projetoemail.repository.EmailRepositoriy;
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
public class EmailService {

    @Autowired
    private EmailRepositoriy emailRepositoriy;

    public List<Email> getAll() {
        return emailRepositoriy.findAll();
    }

    public Optional<Email> getId(Long emailId) {
        return emailRepositoriy.findById(emailId);
    }

    public Email createUsuario(Email email){
        return emailRepositoriy.save(email);
    }

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);

        //mailSender.setUsername("rod.poa@gmail.com");
        //mailSender.setPassword("tlokdgfgpavgccwg");
        Long id = 1L;

        mailSender.setUsername(emailRepositoriy.findById(id).get().getEmail());
        mailSender.setPassword(emailRepositoriy.findById(id).get().getSenha());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.test-connection", "false");

        return mailSender;
    }
    public void create(EmailDTO emailDTO) {

        String msn ="";
        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setFrom(emailDTO.getEmailFrom());
            mensagem.setTo(emailDTO.getEmailTo());
            mensagem.setSubject(emailDTO.getSubject());
            mensagem.setText(emailDTO.getText());
            getJavaMailSender().send(mensagem);
            msn = "Enviado com sucesso";
        } catch (MailException e){
            msn = "Erro no envio: "+ e.getMessage();
        } finally {
            System.out.println(msn);
            //return emailRepositoriy.save(email);
        }
    }
}
