package br.com.conexa.desafio.agendamento.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conexa.desafio.agendamento.protocolo.AgendamentoRequest;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoResponse;
import br.com.conexa.desafio.agendamento.servico.AgendamentoServico;

@CrossOrigin
@RestController
@RequestMapping("/agendamento")
public class AgendamentoControle {

	@Autowired
	private AgendamentoServico agendamentoServico;
	
	@PostMapping
	public AgendamentoResponse agendar(@RequestBody AgendamentoRequest request) {
		return agendamentoServico.agendar(request);
	}
	
}
