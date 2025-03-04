<p align="center">
	

 <img src="https://ik.imagekit.io/ajt99blle/saudehub.jpg?updatedAt=1741109284451" width="700"/>

  
  <h1 align="center"> 🩺 Saúde Hub  </h1>
  
<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-funcionalidades">Funcionalidades</a> •
 <a href="#-modelagem-do-sistema">Modelagem do sistema</a> • 
 <a href="#-como-executar-o-projeto">Como executar</a> • 
<a href="#-deploy">Deploy</a> • 
 <a href="#-tecnologias">Tecnologias</a> •  
 <a href="#licença">Licença</a>
</p>

## 📄 Sobre o Projeto
<p> 
	O SaúdeHub é um sistema de agenda pessoal focado na saúde, ajudando você a organizar consultas, exames e medicamentos de forma prática. Com ele, você pode registrar compromissos médicos, acompanhar exames pendentes e definir lembretes para seus medicamentos, garantindo um melhor controle do seu tratamento.<br><br>
Fácil de usar e seguro, o SaúdeHub reúne tudo o que você precisa para manter sua saúde em dia, em um só lugar! 🚀💙
</p>

## ⚙️ Funcionalidades

O SaúdeHub oferece as seguintes funcionalidades para o gerenciamento de informações de saúde:

* Cadastrar usuários
* Gerenciar consultas, exames e medicamentos

### Usuários (usuários)
- `GET /usuarios`: Retorna todos os usuários.
- `GET /usuarios/:id`: Retorna um usuário pelo ID.
- `GET /usuarios/nome/{nome}`: Retorna um usuário pelo nome.
- `POST /usuarios`: Cadastra um novo usuário.
- `PUT /usuarios/atualizar/:id`: Atualiza um usuário pelo ID.
- `DELETE /usuarios/:id`: Deleta um usuário pelo ID.

### Consultas (consultas)
- `GET /consultas`: Retorna todas as consultas.
- `GET /consultas/:id`: Retorna uma consulta pelo ID.
- `GET /consultas/status/{status}`: Retorna uma consulta por status.
- `POST /consultas`: Registra uma nova consulta.
- `PUT /consultas/:id`: Atualiza uma consulta pelo ID.
- `DELETE /consultas/:id`: Deleta uma consulta pelo ID.

### Exames (exames)
- `GET /exames`: Retorna todos os exames.
- `GET /exames/:id`: Retorna um exame pelo ID.
- `GET /exames/nome/{nome}`: Retorna um exame pelo nome.
- `POST /exames`: Registra um novo exame.
- `PUT /exames/:id`: Atualiza um exame pelo ID.
- `DELETE /exames/:id`: Deleta um exame pelo ID.

### Medicamentos (medicamentos)
- `GET /medicamentos`: Retorna todos os medicamentos.
- `GET /medicamentos/:id`: Retorna um medicamento pelo ID.
- `GET /medicamentos/nome/{nome}`: Retorna um medicamento pelo nome.
- `POST /medicamentos`: Registra um novo medicamento.
- `PUT /medicamentos/:id`: Atualiza um medicamento pelo ID.
- `DELETE /medicamentos/:id`: Deleta um medicamento pelo ID.

Esses endpoints ajudam a centralizar e gerenciar todas as informações de saúde, mantendo o usuário no controle total de seus cuidados.

## 💾 Modelagem do sistema
### Diagram de Classes
```mermaid
classDiagram
    class Usuario {
        +Long id
        +String nome
        +String email
        +String senha
        +LocalDate dataNascimento
        +String telefone
        +String endereco
        +TipoSanguineo tipoSanguineo
        +LocalDateTime criadoEm
        +LocalDateTime atualizadoEm
    }

    class Consulta {
        +Long id
        +LocalDateTime dataConsulta
        +String especialidade
        +String medico
        +StatusConsulta status
        +String observacoes
        +String local
        +LocalDateTime criadoEm
        +LocalDateTime atualizadoEm
    }

    class Exame {
        +Long id
        +String nome
        +LocalDateTime dataExame
        +String local
        +String resultado
        +String observacoes
        +LocalDateTime criadoEm
        +LocalDateTime atualizadoEm
    }

    class Medicamento {
        +Long id
        +String nome
        +String dosagem
        +String periodo
        +String viaAdministracao
        +String instrucoesUso
        +LocalDateTime criadoEm
        +LocalDateTime atualizadoEm
    }

    Usuario "1" -- "0..*" Consulta : possui
    Usuario "1" -- "0..*" Exame : possui
    Usuario "1" -- "0..*" Medicamento : possui
    Consulta "1" -- "1" Usuario : pertence a
    Exame "1" -- "1" Usuario : pertence a
    Medicamento "1" -- "1" Usuario : pertence a

```
### Diagrama Entidade-Relacionamento (DER)
<img src="https://ik.imagekit.io/ajt99blle/saudehub.png?updatedAt=1741113638475" width="700"/>

## ⚡ Como executar o projeto
### Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

- Java 21 ou versão superior
- Maven (ou Gradle, dependendo do seu gerenciador de dependências preferido, caso esteja utilizando outro)
- MySQL 
- IDE como IntelliJ IDEA ou Eclipse 

### Agora é só seguir os seguintes passos:
```bash
# CLone este repositório
$ git clone git@github.com:seu-perfil/saude-hub.git

# Acesse a pasta do projeto no terminal/cmd
$ cd saude-hub

# Instale as dependências 
$ mvn clean install

# Altere as informações do banco de dados no application-dev.properties
spring.datasource.username=seu-username
spring.datasource.password=sua-senha

# Altere para o perfil dev no arquivo application.properties
spring.profiles.active=dev

# Execute a aplicação pelo SaudeHubApplication.java
$ run 
```
A aplicação estará disponível em `http://localhost:8080` juntamente com o swagger.

### **Observação:**
#### Alternando entre os perfis no arquivo application.properties
Para alternar entre as configurações Local e Remota, abra o arquivo application.properties e utilize uma das 2 opções abaixo:
- spring.profiles.active=dev 🡢 O Spring executará a aplicação com a configuração do Banco de dados local (MySQL)
- spring.profiles.active=prod 🡢 O Spring executará a aplicação com a configuração do Banco de dados na nuvem (Render)

## 🚀 Deploy
O deploy da aplicação está disponivel [neste link](https://saudehub.onrender.com)


## 🛠 Tecnologias
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white)
![Render](https://img.shields.io/badge/Render-46E3B7?style=for-the-badge&logo=render&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

## 📝Licença

Feito com ❤️ por Vanessa Ribeiro 👋🏽 [Entre em contato!](https://www.linkedin.com/in/vanessaribeiro-/)

