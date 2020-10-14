package br.com.conexa.desafio.pacientes.servico;

import org.springframework.stereotype.Service;

import br.com.conexa.desafio.pacientes.protocolo.PacienteRequest;
import br.com.conexa.desafio.pacientes.protocolo.PacienteResponse;
import br.com.conexa.desafio.pacientes.protocolo.PacienteResponseList;

@Service
public interface PacienteServico {

	PacienteResponse salvar(PacienteRequest request);
	
	PacienteResponse atualizar(PacienteRequest request);
	
	PacienteResponse obter(Long pacienteId);
	
	void delete(Long pacienteId);
	
	PacienteResponseList todos();
	
}
