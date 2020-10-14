package br.com.conexa.desafio.agendamento.negocio;

import java.util.Set;

import br.com.conexa.desafio.agendamento.protocolo.AgendamentoResponse;
import br.com.conexa.desafio.agendamento.servico.AgendamentoServico;
import br.com.conexa.desafio.medicos.entidade.Medico;

public interface AgendamentoNegocio extends AgendamentoServico {

	Set<AgendamentoResponse> obtemAgendamentosPorMedicoHoje(Medico medico);

}
