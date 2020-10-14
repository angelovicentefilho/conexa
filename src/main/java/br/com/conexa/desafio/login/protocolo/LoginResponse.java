package br.com.conexa.desafio.login.protocolo;

import java.util.Set;

import br.com.conexa.desafio.agendamento.protocolo.AgendamentoResponse;
import br.com.conexa.desafio.utils.ProtocolResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Protocolo de resposta do serviço de Login.
 * 
 * @author Angelo Vicente Filho
 *
 */
@Data
@Builder(setterPrefix = "com")
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements ProtocolResponse {

	private String token;
	private String nome;
	private String especialidade;
	private Set<AgendamentoResponse> agendamentos;
	
}
