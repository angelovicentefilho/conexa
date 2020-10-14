package br.com.conexa.desafio.agendamento.negocio;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.conexa.desafio.agendamento.entidade.Agendamento;
import br.com.conexa.desafio.agendamento.parse.AgendamentoCodec;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoRequest;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoResponse;
import br.com.conexa.desafio.agendamento.repositorio.AgendamentoRepositorio;
import br.com.conexa.desafio.medicos.entidade.Medico;

@Component
public class AgendamentoNegocioImpl implements AgendamentoNegocio {

	@Autowired
	private AgendamentoRepositorio agendamentoRepositorio;

	@Autowired
	private AgendamentoCodec agendamentoCodec;
	
	@Override
	public Set<AgendamentoResponse> obtemAgendamentosPorMedicoHoje(Medico medico) {
		Set<Agendamento> agendamentos =  agendamentoRepositorio.obtemAgendamentosPorMedicoHoje(medico.getMedicoId());
		Set<AgendamentoResponse> responses = new HashSet<>();
		for (Agendamento agendamento : agendamentos) {
			responses.add(agendamentoCodec.encode(agendamento));
		}
		return responses;
	}

	@Override
	public AgendamentoResponse agendar(AgendamentoRequest request) {
		Agendamento agendamento = agendamentoCodec.decode(request);
		agendamentoRepositorio.saveAndFlush(agendamento);
		AgendamentoResponse response = agendamentoCodec.encode(agendamento);
		return response;
	}

}
