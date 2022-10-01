package com.projetoemail.projetoemail.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailFromDTO {

    @NotEmpty(message = "O e-mail deve ser informado")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "A senha deve ser informada")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
