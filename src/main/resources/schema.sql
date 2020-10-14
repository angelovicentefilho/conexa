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

