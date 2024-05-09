## Agendamento de transferência

Aplicação que realiza u agendamento de transferência bancária entre contas, e depois exibe um extrato das operações 
agendadas.

### Tecnologias usadas

#### Backend
- Java 11
- Spring Boot 2.7.18
- JPA
- Hibernate
- H2

#### Frontend
- Angular 17
- Angular material 

### Separação dos fontes

A aplicação está separada entre o modulo do backend e o do frontend, os fontes do backend se encontram na pasta raiz e 
estão a partir da pasta **src**, os fontes do frontend estão a partir da pasta **_front**.

### Subindo o Frontend

Acessar a pasta **_front**, utilizando algum console, quando estiver na pasta rodar o comando: (Necessário ter o node e o 
angular CLI instalados e configurados na máquina)

    npm install

Após rodar este comando, aguardar a instalação dos módulos, após a conclusão da instalação dos módulos rodar o comando
abaixo para subir o frontend.

    npm start

A aplicação irá subir na porta 4200, o pode ser acessada por um navegador pela URL:

    http://localhost:4200/home

### Subindo o backend 

Ir até a classe main da aplicação e rodar, então ela começara a subir na porta **8081**.

### Acesso a o H2

O banco de dados em memória irá carregar alguns dados automaticamente para a conta **12131873**, para acessar a base 
use a url: 

    http://localhost:8081/transfer/h2-console

- Usuário: sa
- Senha: password