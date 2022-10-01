package com.projetoemail.projetoemail.controller;

import com.projetoemail.projetoemail.dto.EmailDTO;
import com.projetoemail.projetoemail.dto.EmailFromDTO;
import com.projetoemail.projetoemail.model.Email;
import com.projetoemail.projetoemail.model.EmailFrom;
import com.projetoemail.projetoemail.service.EmailFromService;
import com.projetoemail.projetoemail.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailFromService emailFromService;

    @Autowired
    private EmailService emailService;

    /*@RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hello from Spring Boot Application");
        message.setTo("rodrigo.gambarra@acad.pucrs.br");
        message.setFrom("rod.poa@gmail.com");

        try {
            Long id = 1L;
            if(!emailFromService.getId(id).isPresent())
                return "/cadastrar-email-from";
            emailFromService.getJavaMailSender().send(message);
            //mailSender.send(message);
            return "enviado-sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "erro-envio";
        }
    }*/

    @PostMapping("/CadastrarEmailFrom")
    public ResponseEntity<EmailFrom> envioEmail(@Valid EmailFromDTO emailFromDTO){
        EmailFrom emailFrom = new EmailFrom();
        BeanUtils.copyProperties(emailFromDTO, emailFrom);
        return new ResponseEntity<>(emailFromService.create(emailFrom) ,HttpStatus.CREATED);
    }
    @PostMapping("/")
    public String envioEmail(@Valid EmailDTO emailDTO){

        EmailFrom emailFrom = emailFromService.getByEmail(emailDTO.getEmailFrom());
        if(emailFrom != null){
            Email email = new Email();
            BeanUtils.copyProperties(emailDTO, email); //Converte emailDTO para email
            emailService.create(emailFrom, email);
            return "enviado-sucesso";
        }
        //ResponseEntity<EmailDTO> tResponseEntity = new ResponseEntity<>(emailDTO, HttpStatus.CREATED);//Retorna Json com objeto
        return "erro-envio";
    }

    @GetMapping("/")
    public ResponseEntity<List<Email>> verTodosEmails(){
        return new ResponseEntity<>(emailService.emailListAll(), HttpStatus.OK);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<Email> verEmails(@PathVariable (value = "emailId")Long emailId){
        Optional<Email> emailOptional = emailService.findById(emailId);
        if(emailOptional.isPresent()){
            return new ResponseEntity<>(emailOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enviar")
    public String entrarPagina(){
        if(emailFromService.getAll().isEmpty())
            return "cadastrar-email-from";
        return "enviar-email";
    }

    @PostMapping("/enviar")
    public String envioEmailNavegador(@Valid EmailDTO emailDTO){

        EmailFrom emailFrom = emailFromService.getByEmail(emailDTO.getEmailFrom());
        if(emailFrom != null){
            Email email = new Email();
            BeanUtils.copyProperties(emailDTO, email); //Converte emailDTO para email
            emailService.create(emailFrom, email);
            return "enviado-sucesso";
        }
        //ResponseEntity<EmailDTO> tResponseEntity = new ResponseEntity<>(emailDTO, HttpStatus.CREATED);//Retorna Json com objeto
        return "erro-envio";
    }
}