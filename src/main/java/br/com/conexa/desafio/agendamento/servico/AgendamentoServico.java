package br.com.conexa.desafio.agendamento.servico;

import org.springframework.stereotype.Service;

import br.com.conexa.desafio.agendamento.protocolo.AgendamentoRequest;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoResponse;

@Service
public interface AgendamentoServico {

	AgendamentoResponse agendar(AgendamentoRequest request);
	
}
