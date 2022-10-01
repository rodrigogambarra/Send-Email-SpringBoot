package com.projetoemail.projetoemail.service;

import com.projetoemail.projetoemail.dto.EmailDTO;
import com.projetoemail.projetoemail.model.Email;
import com.projetoemail.projetoemail.model.EmailFrom;
import com.projetoemail.projetoemail.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

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
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setFrom(email.getEmailFrom());
            mensagem.setTo(email.getEmailTo());
            mensagem.setSubject(email.getSubject());
            mensagem.setText(email.getText());
            emailSenderService.getJavaMailSender(emailFrom).send(mensagem);
            msn = "Enviado com sucesso";
        } catch (MailException e){
            msn = "Erro no envio: "+ e.getMessage();
        } finally {
            System.out.println(msn);
            emailRepository.save(email);
        }
    }
}
