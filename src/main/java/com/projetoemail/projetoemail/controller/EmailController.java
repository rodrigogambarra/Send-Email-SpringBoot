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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/CadastrarEmailFrom")
    public String cadastrarEmailFrom(Model model){
        model.addAttribute("emailFromDTO", new EmailFromDTO());
        return "cadastrar-email-from";
    }

    @PostMapping("/CadastrarEmailFrom")
    public String envioEmail(@Valid EmailFromDTO emailFromDTO, BindingResult result, Model model, RedirectAttributes attributes){

        if(result.hasErrors())
            return "cadastrar-email-from";

        if(!emailFromService.validaEmail(emailFromDTO.getEmail())) {
            model.addAttribute("emailMensagem", "Deve ser e-mail do Gmail!");
            return "cadastrar-email-from";
        }
        EmailFrom emailFrom = new EmailFrom();
        BeanUtils.copyProperties(emailFromDTO, emailFrom);
        emailFromService.create(emailFrom);
        //model.addAttribute("mensagemSucesso", "E-mail cadastrado com sucesso!");
        model.addAttribute("emailDTO", new EmailDTO());
        return "enviar-email";
    }
    @PostMapping("/")
    public String envioEmail(@Valid EmailDTO emailDTO, BindingResult result, Model model){

        if(result.hasErrors()){
            return "enviar-email";
        }

        EmailFrom emailFrom = emailFromService.getByEmail(emailDTO.getEmailFrom());
        if(emailFrom == null){
            model.addAttribute("emailMensagem", "E-mail não cadastrado!");
            return "enviar-email";
        }
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email); //Converte emailDTO para email
        emailService.create(emailFrom, email);
        return "enviado-sucesso";
        //ResponseEntity<EmailDTO> tResponseEntity = new ResponseEntity<>(emailDTO, HttpStatus.CREATED);//Retorna Json com objeto
    }

    @GetMapping("/emailsEnviados")
    public ResponseEntity<List<Email>> verTodosEmails(){
        return new ResponseEntity<>(emailService.emailListAll(), HttpStatus.OK);
    }

    @GetMapping("/emailsFrom")
    public ResponseEntity<List<EmailFrom>> verTodosEmailsFrom(){
        return new ResponseEntity<>(emailFromService.getAll(), HttpStatus.OK);
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

    @GetMapping("/enviarEmail")
    public String entrarPagina(Model model){
        if(emailFromService.getAll().isEmpty()) {
            model.addAttribute("emailFromDTO", new EmailFromDTO());
            return "cadastrar-email-from";
        }
        model.addAttribute("emailDTO", new EmailDTO());
        return "enviar-email";
    }

    @PostMapping("/enviarEmail")
    public String envioEmailNavegador(@Valid EmailDTO emailDTO, BindingResult result, Model model){

        if(result.hasErrors()){
            return "enviar-email";
        }

        EmailFrom emailFrom = emailFromService.getByEmail(emailDTO.getEmailFrom());
        if(emailFrom == null){
            model.addAttribute("emailMensagem", "E-mail não cadastrado!");
            return "enviar-email";
        }
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email); //Converte emailDTO para email
        emailService.create(emailFrom, email);
        return "enviado-sucesso";
        //ResponseEntity<EmailDTO> tResponseEntity = new ResponseEntity<>(emailDTO, HttpStatus.CREATED);//Retorna Json com objeto
    }
}