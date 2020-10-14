package br.com.conexa.desafio.agendamento.parse;

import org.springframework.stereotype.Component;

import br.com.conexa.desafio.agendamento.entidade.Agendamento;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoRequest;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoResponse;

@Component
public class AgendamentoCodec {

	private AgendamentoDecode decode = new AgendamentoDecode();
	private AgendamentoEncode encode = new AgendamentoEncode();
	
	public Agendamento decode(AgendamentoRequest request) {
		return decode.decode(request);
	}
	
	public AgendamentoResponse encode(Agendamento agendamento) {
		return encode.encode(agendamento);
	}
	
}
