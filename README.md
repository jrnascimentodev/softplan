# Seleção Dev Java
## Software e Ferramentas
* Java 11
* Spring Tool Suite (STS)
* MySQL
* Docker
* Postman
* Thymeleaf
* Spring MVC
* Spring Security
* Spring Data JPA
* Maven
## Executando projetos

* Faça um clone do projeto

## Executar no Docker 

A imagem está disponivel em https://hub.docker.com/r/jrnascimento/softperson

docker pull jrnascimento/softperson

Crie a imagem e execute containers

mvn jib:dockerBuild

docker-compose up --build

O servidor esta sendo executado em https://localhost:8080/login.html

Swagger UI estará disponível em https://localhost:8080/swagger-ui.html

