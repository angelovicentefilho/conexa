package br.com.conexa.desafio.pacientes.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.conexa.desafio.pacientes.protocolo.PacienteRequest;
import br.com.conexa.desafio.pacientes.protocolo.PacienteResponse;
import br.com.conexa.desafio.pacientes.protocolo.PacienteResponseList;
import br.com.conexa.desafio.pacientes.servico.PacienteServico;

@RestController
public class PacienteControle {

	@Autowired
	private PacienteServico pacienteServico;
	
	@PostMapping("/pacientes")
	public PacienteResponse salvar(@RequestBody PacienteRequest request) {
		return pacienteServico.salvar(request);
	}
	
	@PutMapping("/pacientes")
	public PacienteResponse atualizar(@RequestBody PacienteRequest request) {
		return pacienteServico.atualizar(request);
	}
	
	@DeleteMapping("/pacientes/{pacienteId}")
	public void deletar(@PathVariable("pacienteId") Long pacienteId) {
		pacienteServico.delete(pacienteId);
	}
	
	@GetMapping("/pacientes/{pacienteId}")
	public PacienteResponse obter(@PathVariable("pacienteId") Long pacienteId) {
		return pacienteServico.obter(pacienteId);
	}
	
	@GetMapping("/pacientes")
	public PacienteResponseList todos() {
		return pacienteServico.todos();
	}
	
}
