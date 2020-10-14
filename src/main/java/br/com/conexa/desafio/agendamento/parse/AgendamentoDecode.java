package br.com.conexa.desafio.agendamento.parse;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.conexa.desafio.agendamento.entidade.Agendamento;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoRequest;

public class AgendamentoDecode {

	public Agendamento decode(AgendamentoRequest request) {
		Agendamento agendamento = new Agendamento();
		try {
			agendamento.setDataHoraAtendimento(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getDataHoraAtendimento()));
			agendamento.setPacienteId(request.getIdPaciente());
			agendamento.setMedicoId(request.getMedicoId());
			return agendamento;
		} catch (ParseException e) {
			throw new RuntimeException("Não pode fazer o parser do agendamento.", e);
		}
	}

}
