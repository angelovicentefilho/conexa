package br.com.conexa.desafio.agendamento.parse;

import java.text.SimpleDateFormat;

import br.com.conexa.desafio.agendamento.entidade.Agendamento;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoResponse;

public class AgendamentoEncode {

	public AgendamentoResponse encode(Agendamento agendamento) {
		return AgendamentoResponse.builder()
				.comDataHoraAtendimento(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(agendamento.getDataHoraAtendimento()))
				.comIdPaciente(agendamento.getPacienteId())
				.build();
	}

}
