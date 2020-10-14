package br.com.conexa.desafio.pacientes.parse;

import java.util.UUID;

import br.com.conexa.desafio.pacientes.entidade.Paciente;
import br.com.conexa.desafio.pacientes.protocolo.PacienteRequest;

public class PacienteDecode {

	public Paciente decode(PacienteRequest request) {
		Paciente paciente = new Paciente();
		paciente.setCpf(request.getCpf());
		paciente.setIdade(request.getIdade());
		paciente.setNome(request.getNome());
		paciente.setTelefone(request.getTelefone());
		paciente.setUuid(request.getUuid() == null ? UUID.randomUUID().toString().substring(0,6) : request.getUuid());
		paciente.setPacienteId(request.getPacienteId());
		return paciente;
	}
}
