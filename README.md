## Agendamento de transferência

Aplicação que realiza u agendamento de transferência bancária entre contas, e depois exibe um extrato das operações 
agendadas.

### Tecnologias usadas

#### Backend
- Java 11
- Spring Boot 3.2.4
- JPA
- Hibernate
- H2

#### Frontend
- Angular 17
- Angular material 

### Separação dos fontes

A aplicação está separada entre o modulo do backend e o do frontend, os fontes do backend se encontram na pasta raiz e 
estão a partir da pasta src, os fontes do frontend estão a partir da pasta _front.

### Subindo o Frontend

Acessar a pasta _front, utilizando algum console, quando estiver na pasta rodar o comando: (Necessário ter o node e o 
angular CLI instalados e configurados na máquina)

    npm install

Após rodar este comando, aguardar a instalação dos módulos, após a conclusão da instalação dos módulos rodar o comando
abaixo para subir o frontend.

    npm start

A aplicação irá subir na porta 4200, o pode ser acessada por um navegador pela URL:

    http://localhost:4200/home

### Subindo o backend 

Rodar a classe main e a aplicação irá começar a subir, a aplicação sobe na porta 8081. 