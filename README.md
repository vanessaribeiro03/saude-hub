<p align="center">
	

 <img src="https://ik.imagekit.io/ajt99blle/saudehub.jpg?updatedAt=1741109284451" width="700"/>

  
  <h1 align="center"> 🩺 Saúde Hub  </h1>
  
<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-funcionalidades">Funcionalidades</a> •
 <a href="#-layout">Layout</a> • 
 <a href="#-como-executar-o-projeto">Como executar</a> • 
 <a href="#-tecnologias">Tecnologias</a> • 
 <a href="#-autora">Autora</a> • 
 <a href="#user-content--licença">Licença</a>
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
