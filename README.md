# 🌍 SAFEQUAKE V1

Sistema de monitoramento sísmico inteligente com alertas em tempo real baseado em geolocalização e magnitude de terremotos.

## 📚 Sobre o Projeto

O **SafeQuake** é um sistema desenvolvido para detectar eventos sísmicos com base em dados da USGS (futuramente integrados) e/ou entrada manual (iot). Usuários cadastrados recebem alertas automáticos caso estejam localizados próximos a abalos classificados como **ALTO** ou **CRÍTICO**, com base na magnitude e na distância em relação ao epicentro.
> Projeto acadêmico da FIAP — Global Solution 2025.

---

## ⚙️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3+
- Spring Security (com JWT)
- Spring Data JPA
- Oracle Database
- Swagger / OpenAPI
- Maven

---

## 📦 Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/caioliang/safequake_api.git
cd safequake_api
```

### 2. Configure o acesso ao banco Oracle da FIAP

#### Opção A: Usando variáveis de ambiente

```bash
export DB_USERNAME=seu_usuario
export DB_PASSWORD=sua_senha
```
> Obrigatório possuir cadastro no BD ORACLE FIAP

### 3. Compile o projeto com Maven

```bash
mvn clean install
```

### 4. Execute a aplicação

```bash
mvn spring-boot:run
```

> A aplicação ficará disponível em: `http://localhost:8080`

---

## 🔐 Autenticação

A aplicação utiliza autenticação via **JWT**. Para acessar rotas protegidas:

1. Crie um usuário via `/users`
2. Faça login via `/login` com email e senha
3. Copie o token JWT retornado
4. Use o token no header `Authorization: Bearer {token}`

---

## 🚨 Endpoints Principais

| Método | Rota | Descrição |
|--------|------|-----------|
| POST   | `/users` | Cadastro de usuários |
| POST   | `/login` | Autenticação e geração de token JWT |
| POST   | `/earthquakes/manual` | Cadastro manual de terremoto |
| GET    | `/earthquakes/classified` | Lista terremotos classificados com paginação e ordenação |
| GET    | `/alert?lat=X&lon=Y` | Lista terremotos próximos de um usuário a até 70km com classificação ALTO ou CRÍTICO |

---

## 📘 Documentação Swagger

Acesse a documentação da API em:

```
http://localhost:8080/swagger-ui.html
```

---

## 👨‍💻 Autores

Desenvolvido por:

- [Caio Liang](https://github.com/caioliang)
- [Allan Brito](https://github.com/Allanbm100)
- [Levi Magni](https://github.com/levmn)
