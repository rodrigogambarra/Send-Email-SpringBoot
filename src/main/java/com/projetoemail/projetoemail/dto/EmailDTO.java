package com.projetoemail.projetoemail.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class EmailDTO {

    @NotEmpty(message = "O e-mail from deve ser informado")
    @Email(message = "Email inválido")
    private String emailFrom;
    @NotEmpty(message = "O e-mail do destinatário deve ser informado")
    @Email(message = "Email inválido")
    private String emailTo;
    @NotEmpty(message = "O assunto deve ser informado")
    private String subject;
    @NotEmpty(message = "A mensagem deve ser informada")
    private String text;


    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
