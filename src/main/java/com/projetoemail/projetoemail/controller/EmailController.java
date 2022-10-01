package com.projetoemail.projetoemail.controller;

import com.projetoemail.projetoemail.dto.EmailDTO;
import com.projetoemail.projetoemail.model.Email;
import com.projetoemail.projetoemail.service.EmailService;
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

    //@Autowired private JavaMailSender mailSender;
    @Autowired
    private EmailService emailService;

    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hello from Spring Boot Application");
        message.setTo("rodrigo.gambarra@acad.pucrs.br");
        message.setFrom("rod.poa@gmail.com");

        try {
            Long id = 1L;
            if(!emailService.getId(id).isPresent())
                return "/cadastrar-usuario-master";
            emailService.getJavaMailSender().send(message);
            //mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

    @PostMapping("/CadastrarUsuario")
    public ResponseEntity<Email> envioEmail(@RequestBody Email email){
        return new ResponseEntity<>(emailService.createUsuario(email) ,HttpStatus.CREATED);
    }
    @PostMapping("/")
    public ResponseEntity<EmailDTO> envioEmail(@RequestBody @Valid EmailDTO emailDTO){
        emailService.create(emailDTO);
        ResponseEntity<EmailDTO> tResponseEntity = new ResponseEntity<>(emailDTO, HttpStatus.CREATED);//Retorna Json com objeto
        return tResponseEntity;
    }

    @GetMapping("/")
    public ResponseEntity<List<Email>> verTodosEmails(){
        return new ResponseEntity<>(emailService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<Email> verEmails(@PathVariable (value = "emailId")Long emailId){
        Optional<Email> emailModelOptional = emailService.getId(emailId);
        if(emailModelOptional.isPresent()){
            return new ResponseEntity<>(emailModelOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enviar")
    public String entrarPagina(){
        Long id = 1L;
        if(!emailService.getId(id).isPresent())
            return "cadastrar-usuario-master";
        return "enviar-email";
    }

    @PostMapping("/enviar")
    public ResponseEntity<EmailDTO> envioEmailNavegador(@Valid EmailDTO emailDTO){
        emailService.create(emailDTO);
        ResponseEntity<EmailDTO> tResponseEntity = new ResponseEntity<>(emailDTO, HttpStatus.CREATED);//Retorna Json com objeto
        return tResponseEntity;
    }
}