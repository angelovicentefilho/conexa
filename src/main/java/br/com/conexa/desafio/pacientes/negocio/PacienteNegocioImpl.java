package br.com.conexa.desafio.pacientes.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.conexa.desafio.pacientes.parse.PacienteCodec;
import br.com.conexa.desafio.pacientes.protocolo.PacienteRequest;
import br.com.conexa.desafio.pacientes.protocolo.PacienteResponse;
import br.com.conexa.desafio.pacientes.protocolo.PacienteResponseList;
import br.com.conexa.desafio.pacientes.repositorio.PacienteRepositorio;

@Component
public class PacienteNegocioImpl implements PacienteNegocio {

	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	
	@Autowired
	private PacienteCodec codec;
	
	@Override
	public PacienteResponse salvar(PacienteRequest request) {
		return codec.encode(pacienteRepositorio.saveAndFlush(codec.decode(request)));
	}

	@Override
	public PacienteResponse atualizar(PacienteRequest request) {
		return codec.encode(pacienteRepositorio.saveAndFlush(codec.decode(request)));
	}

	@Override
	public PacienteResponse obter(Long pacienteId) {
		return codec.encode(pacienteRepositorio.getOne(pacienteId));
	}

	@Override
	public void delete(Long pacienteId) {
		pacienteRepositorio.delete(pacienteRepositorio.getOne(pacienteId));
	}

	@Override
	public PacienteResponseList todos() {
		return codec.encode(pacienteRepositorio.findAll());
	}

}
