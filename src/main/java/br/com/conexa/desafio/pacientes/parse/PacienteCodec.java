package br.com.conexa.desafio.pacientes.parse;


import java.util.List;

import org.springframework.stereotype.Component;

import br.com.conexa.desafio.pacientes.entidade.Paciente;
import br.com.conexa.desafio.pacientes.protocolo.PacienteRequest;
import br.com.conexa.desafio.pacientes.protocolo.PacienteResponse;
import br.com.conexa.desafio.pacientes.protocolo.PacienteResponseList;

@Component
public class PacienteCodec {

	private PacienteEncode encoder = new PacienteEncode();
	private PacienteDecode decoder = new PacienteDecode();
	
	public PacienteResponse encode(Paciente paciente) {
		return this.encoder.encode(paciente);
	}
	
	public PacienteResponseList encode(List<Paciente> pacientes) {
		return this.encoder.encode(pacientes);
	}
	
	
	public Paciente decode(PacienteRequest request) {
		return this.decoder.decode(request);
	}
	
}
