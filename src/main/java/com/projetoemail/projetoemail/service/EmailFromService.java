package com.projetoemail.projetoemail.service;

import com.projetoemail.projetoemail.model.EmailFrom;
import com.projetoemail.projetoemail.repository.EmailFromRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmailFromService {

    @Autowired
    private EmailFromRepository emailFromRepository;

    public List<EmailFrom> getAll() {
        return emailFromRepository.findAll();
    }

    public Optional<EmailFrom> getId(Long Id) {
        return emailFromRepository.findById(Id);
    }

    public String create(EmailFrom emailFrom){
        emailFromRepository.save(emailFrom);
        return "E-mail cadastrado com sucesso!";
    }

    public EmailFrom getByEmail(String email){
        return emailFromRepository.findByEmail(email);
    }

    public boolean validaEmail(String email){
        boolean valor = false;
        if(email.contains("@gmail.com"))
            valor = true;
        return valor;
    }
}
