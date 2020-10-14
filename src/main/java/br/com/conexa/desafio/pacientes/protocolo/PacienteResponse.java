package br.com.conexa.desafio.pacientes.protocolo;

import br.com.conexa.desafio.utils.ProtocolResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(setterPrefix = "com")
public class PacienteResponse implements ProtocolResponse {

	private String nome;
	private String cpf;
	private String telefone;
	private Integer idade;
	
}
