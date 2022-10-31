Microserviço contém as regras de autorização de procedimento e é consumido pelo web app webappautorizacaoprocedimentos

Tecnologias
Java 11
Spring Boot 2.7.5
Banco de dados H2
jackson
lombok
liquibase
junit
Mockito

Executando o projeto
Na raiz do projeto onde está o pom, executar o comando  mvn spring-boot:run
Foi testado no maven 3.8.6

Banco de dados
Os scripts do liquibase para criação de tabelas e carga de dados se encontram no diretório /msautorizacaoprocedimentos/src/main/resources/db/changelog

Testes unitários
msautorizacaoprocedimentos/src/test/java/com/fabao/operadora/saude/




