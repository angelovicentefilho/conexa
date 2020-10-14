# Desafio Conexa

Em primeiro lugar queria agradecer a oportunidade, porém, estou um pouco sem tempo no momento para fazer tudo que eu queria nesse desafio.

Então desenvolvi em monolitico mesmo, mas com camadas bem separadas e utilizando os principios de S.O.L.I.D, o que facilita muito na hora de divitir em micro serviços.

## Os pacotes

Os pacotes estão divididos em funcionalidades

* Agendamento
* Exceções
* Login
* Médicos
* Pacientes
* Segurança
* Utils

Com essa separação fica facil posteriormente pegar cada um desses pacotes e dividir em micro serviços.

## As camadas

O sistema funciona em camadas bem definidas em cada funcionalidade:

* __Controle__: Esta camada de apresentação é onde estão os endpoints de acesso.
* __Entidade__: Esta é a camada de dominio, representação da tabela em anotações JPA.
* __Negocio__: Nessa camada está a regra de negocio da funcionalidade, nela se encontra a _interface_ de negocio que é utilizada para comunicação horizontal, isto é, entre regras de negocio.
* __Parse__: Isto é apenas um artefato, ele é responsável de pegar o objeto de transferência, ou seja, o protocolo de comunicação Request/Response e transformar em entidade, essa camada deveria ser absorvida em um _@ControllerAdvice_, porém, não tive tempo para isso.
* __Protocolo__: Esse é o artefato de comunicação, eu não utilizo a palavra _DTO_, pois, isso geralmente irá para o cliente, então ele entenderá mais facilmente.
* __Repositorio__: Camada de acesso a base de dados e controle transacional.
* __Serviço__: Eu faço distinção dessa camada, pois, ela é a camada de acesso a camada de apresentação, nem tudo que temos na camada de negocio pode chegar até a apresentação, então, eu tenho essa camada para limitar o acesso aos controladores.

## Ciclo de Vida

No sistema desenvolvido, o ciclo de vida se resume ao seguinte processo:

Chamada ao controller, que injeta um serviço, esse por sua vez é uma interface de negocio que implementa a regra necessária usando artefatos para isso.

Exemplos:

```Java
@RestController
public class ClasseControle {

    @Autowired
    private ClasseServico servico;

    @GetMapping("/test")
    public TestResponse exec() {
        return servico.exec();
    }
}
```

```Java
@Service
public interface ClasseServico {
    /**
    * Esse método será acessado por controller e
    * classes de negocio.
    */
    TestResponse exec();

}
```

```Java
public interface ClasseNegocio extends ClasseServico {
    /**
    * Esse método somente será acessado pelas classes de
    * negócio. Um controlador não oderá acessar ele.
    */
    TestResponseList execList();
}
```

```Java
@Component
public class ClasseNegocioImpl implements ClasseNegocio {

    /**
    * Somente essa camada acessa o repositorio
    * da sua funcionalidade.
    **/
   @Autowired
   private ClasseRepositorio repositorio;

    /**
    * Regra de negocio que o controlador verá.
    **/
    @Override
    public TestResponse exec() {
        // Preparar dados (Decode)
        // Validar dados (PreValidators)
        // Regra de negocio
        // Validar dados (PosValidators)
        // Preparar dados (Encode)
        // Enviar dados (Builder)
        return TestResponse.builder().build();
    }

    /**
    * Regra de negocio que apenas outro negocio verá.
    **/
    @Override
    public TestResponseList execList() {
        return TestResponseList.builder().build();
    }
}
```

```Java
@Component
public class OutraClassNegocioImpl implements OutraClasseNegocio {

    @Autowired
    private OutraClasseRepositorio repositorio;

    @Autowired
    private ClasseNegocio classeNegocio;

    @Override
    public OutroTestResponse exec() {
        TestResponseList list = negocio.execList();
        return OutroTestResponse
                  .builder()
                  .withTestResponseList(list)
                  .build();
    }

}
```

Não utilizei nenhum validador, porém, é um arterfato que também utilizo em regras de negocio, quando são validadores de negocio, e previamente eles passam por um validador através de interceptadores.

## Os Testes

Crie os principais testes de integração, testei os endpoints e também algumas regras de negócio. Não acrescentei o _.gitlab-ci.yml_, por não saber se havia algum _runner_ para saber se iria rodar os estágios. Minha base de teste e minha maquina utilizei o seguinte:

```yml
image: maven:latest

stages:
  - build
  - test
  - run

variables:
  MAVEN_CLI_OPTS: "-s .m2/settings.xml --batch-mode"
  MAVEN_OPTS: "-Dmaven.repo.local=.m2/repository"

cache:
  paths:
    - .m2/repository/
    - target/

build:
  stage: build
  script:
    - mvn $MAVEN_CLI_OPTS compile

test:
  stage: test
  script:
    - mvn $MAVEN_CLI_OPTS test

run:
  stage: run
  script:
    - mvn $MAVEN_CLI_OPTS package
    - mvn $MAVEN_CLI_OPTS exec:java -Dexec.mainClass="br.com.conexa.desafio.ConexaApplication"
```

## O Banco de Dados

Como não tenho o banco de dados MySQL na minha máquina utilizei uma Docker para ele, que se encontra na raiz do projeto como _docker-compose.yml_, no seu conteudo deve ser alterado o valor do volume, pois mapei para a minha maquina.

```yml
version: '3'

services:
  mysqlsrv:
    image: mysql:5.7
    environment:
      MYSQL_ROOT_PASSWORD: "root"
      MYSQL_DATABASE: "conexa_desafio"
    ports:
      - "3306:3306"
    volumes:
      - /home/angelo/clients/particular/rapid/conexa/docker/volumes:/var/lib/mysql    
```

Comando para executar: `docker-compose up`

Para acessar a docker: `docker exec -it` ``<image_name> bash``

Entrar no mysql: `mysql -uroot -proot conexa_desafio`

## O Schema DDL e Data

A fim de facilitar a utilização do teste, e a inicialização do mesmo, ao executar o aplicativo, que foi desenvolvido no STS versão 4, e testado no eclipse e netbeans, o sistema criará a base de dados caso não exista e fará a primeira carga das tabelas, conforme configurado no `application.properties`

```yml
spring.datasource.url=jdbc:mysql://localhost:3306/conexa_desafio?createDatabaseIfNotExist=true
```
Caso seja mudado a base de dados deve-se mudar a url do datasource também.

Por default, ele executará o arquivo `schema.sql` para criar o DDL da base de dados.

Conteudo:

```SQL
-- CREATE DATABASE
CREATE DATABASE IF NOT EXISTS conexa_desafio;

-- CREATE TABLES
DROP TABLE IF EXISTS agendamentos;
DROP TABLE IF EXISTS medicos;
DROP TABLE IF EXISTS pacientes;

CREATE TABLE medicos (
  medico_id bigint(20) NOT NULL AUTO_INCREMENT,
  especialidade varchar(255) DEFAULT NULL,
  nome varchar(255) DEFAULT NULL,
  senha varchar(255) DEFAULT NULL,
  token varchar(255) DEFAULT NULL,
  usuario varchar(255) DEFAULT NULL,
  PRIMARY KEY (medico_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE pacientes (
  paciente_id bigint(20) NOT NULL AUTO_INCREMENT,
  id_paciente varchar(255) DEFAULT NULL,
  cpf varchar(255) DEFAULT NULL,
  idade int(11) DEFAULT NULL,
  nome varchar(255) DEFAULT NULL,
  telefone varchar(11) DEFAULT NULL,
  PRIMARY KEY (paciente_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE agendamentos (
  agendamento_id bigint(20) NOT NULL AUTO_INCREMENT,
  data_hora_atendimento datetime(6) DEFAULT NULL,
  medico_id bigint(20) DEFAULT NULL,
  paciente_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (agendamento_id),
  KEY FK_MEDICO_MEDICO (medico_id),
  KEY FK_MEDICO_PACIENTE (paciente_id),
  CONSTRAINT FK_MEDICO_PACIENTE FOREIGN KEY (paciente_id) REFERENCES pacientes (paciente_id),
  CONSTRAINT FK_MEDICO_MEDICO FOREIGN KEY (medico_id) REFERENCES medicos (medico_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```
Não me foquei nos campos das tabelas, e suas quantidades de caracteres, então fui criando com o default mesmo.

Após executar o script de schema, ele também executará o script de primeira carga, toda vez que o sistema for iniciado, afinal é um teste, caso não seja necessário, remova o _always_ do `application.properties`

Conteudo do script de carga:

```SQL
-- Médicos
INSERT INTO medicos (especialidade, nome, senha, usuario) VALUES ('Cardiologista', 'Augusto', '12374', 'augusto@email.com');
INSERT INTO medicos (especialidade, nome, senha, usuario) VALUES ('Urologista', 'Vicente', '12374', 'vicente@email.com');

-- Pacientes
INSERT INTO pacientes (id_paciente, cpf, idade, nome, telefone) VALUES ('3u482f', '11111111111', 20, 'Paciente 1', '4855555555');
INSERT INTO pacientes (id_paciente, cpf, idade, nome, telefone) VALUES ('4d775a', '22222222222', 35, 'Paciente 2', '4855555551');
INSERT INTO pacientes (id_paciente, cpf, idade, nome, telefone) VALUES ('490ud3', '33333333333', 60, 'Paciente 3', '4855555552');
INSERT INTO pacientes (id_paciente, cpf, idade, nome, telefone) VALUES ('ad58yw', '44444444444', 70, 'Paciente 4', '4855555553');

-- Agendamentos
INSERT INTO agendamentos (data_hora_atendimento, medico_id, paciente_id) VALUES (now(), 1, 1);
INSERT INTO agendamentos (data_hora_atendimento, medico_id, paciente_id) VALUES (now(), 1, 2);
```

## Sobre a programação

São exatamente 4:32 da manhã de sábado, nesse final de semana terei que trabalhar então, me esforcei o máximo entre as 18:00 até agora, afinal eu prometi para o André que iria terminar, não sou acostumado a programar em português, mas como o requisito estava fiz o maximo para parecer coerente e entendivel.


## Teste via POSTMAN

Irá na raiz do projeto o json de configuração do Postman

## As tecnologias

* Java 8
* Spring Boot
* Spring Security
* Docker
* MySQL 5.7
* Maven
* JUnit 5

## Agradecimentos

Desde já agradeço a oportunidade e espero que estaja aceitavel pelo tempo que eu tive para fazê-lo, sei que é bastante camadas, porém, eu já passei por problemas pro falta de artefatos e não pela grande quantidade deles.

Fiz tudo pensando em dividir em microserviços e comunicar entre cada funcionalidade atraves de pub/sub.