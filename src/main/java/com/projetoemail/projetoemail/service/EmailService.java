package com.projetoemail.projetoemail.service;

import com.projetoemail.projetoemail.model.Email;
import com.projetoemail.projetoemail.model.EmailFrom;
import com.projetoemail.projetoemail.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    public List<Email> emailListAll(){
        return emailRepository.findAll();
    }

    public Email createEmail(Email email){
        return emailRepository.save(email);
    }

    public Optional<Email> findById(Long id){
        return emailRepository.findById(id);
    }

    public void create(EmailFrom emailFrom, Email email) {

        String msn ="";
        try {
            JavaMailSender javaMailSender = emailSenderService.getJavaMailSender(emailFrom);
            //FORMATO HTML
            /*
            MimeMessage mensagem = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(mensagem, true);
            helper.setFrom(email.getEmailFrom());
            helper.setTo(email.getEmailTo());
            helper.setText(email.getText(),true);
            javaMailSender.send(mensagem);
            */
            //FORMATO TEXTO COMUM

            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setFrom(email.getEmailFrom());
            mensagem.setTo(email.getEmailTo());
            mensagem.setSubject(email.getSubject());
            mensagem.setText(email.getText());
            javaMailSender.send(mensagem);

            msn = "Enviado com sucesso";
            //}catch (MessagingException f){
            //    msn = "Erro no envio: "+ f.getMessage();
            //}
        }catch (MailException e){
            msn = "Erro no envio: "+ e.getMessage();
        } finally {
            System.out.println(msn);
            emailRepository.save(email);
        }
    }
}
