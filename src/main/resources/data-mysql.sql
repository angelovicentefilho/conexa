
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

