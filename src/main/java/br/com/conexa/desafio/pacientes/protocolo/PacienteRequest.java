package br.com.conexa.desafio.pacientes.protocolo;

import br.com.conexa.desafio.utils.ProtocolRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(setterPrefix = "com")
public class PacienteRequest implements ProtocolRequest {

	private Long pacienteId;
	private String nome;
	private String cpf;
	private String telefone;
	private Integer idade;
	private String uuid;
}
