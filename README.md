# Projeto que faz conexão com banco MySQL e envia e-mails utilizando um e-mail do gmail como e-mail from


### Caso não exista um "E-mail from" cadastrado no sistema, então será direcionado para uma tela onde deverá ser cadastrado um (e-mail from)

#### Para cadastrar um (e-mail from) é necessário uma conta do gmail autenticada por duas etapas e que seja criada a senha para App nessa conta.
Após inserir o e-mail dessa conta e a (senha para App) extraída do gmail, então o sistema irá cadastrar no banco de dados essas
informações para utilizará no envio dos e-mails.

### 1. Para iniciar, deve-se entrar no endereço:

http://localhost:8080/email/enviarEmail

### 2. Listar todos e-mails já enviados:

http://localhost:8080/email/emailsEnviados

### 2. Listar todos e-mails froms cadastrados:

http://localhost:8080/email/emailsFrom

![tela de preenchimento do e-mail](https://user-images.githubusercontent.com/47284142/193813103-5a8ebc36-5d32-456d-886b-e94dfc2775e7.png)
![tela de sucesso no envio](https://user-images.githubusercontent.com/47284142/193813202-c3e5d07a-a842-4936-8d48-9947d3547a8d.png)
