package br.com.conexa.desafio.pacientes.parse;

import java.util.ArrayList;
import java.util.List;

import br.com.conexa.desafio.pacientes.entidade.Paciente;
import br.com.conexa.desafio.pacientes.protocolo.PacienteResponse;
import br.com.conexa.desafio.pacientes.protocolo.PacienteResponseList;

public class PacienteEncode {

	public PacienteResponse encode(Paciente paciente) {
		return PacienteResponse.builder().comCpf(paciente.getCpf()).comIdade(paciente.getIdade())
				.comNome(paciente.getNome()).comTelefone(paciente.getTelefone()).build();
	}

	public PacienteResponseList encode(List<Paciente> pacientes) {
		List<PacienteResponse> responses = new ArrayList<>();
		for (Paciente paciente : pacientes) {
			responses.add(encode(paciente));
		}
		return PacienteResponseList.builder().comPacientes(responses).build();
	}

}
