package br.com.conexa.desafio.agendamento.protocolo;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.conexa.desafio.utils.ProtocolResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(setterPrefix = "com")
public class AgendamentoResponse implements ProtocolResponse {

	@JsonProperty("id_paciente")
	private Long idPaciente;
	@JsonProperty("data_hora_atendimento")
	private String dataHoraAtendimento;

}
