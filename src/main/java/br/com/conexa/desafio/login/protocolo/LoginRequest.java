package br.com.conexa.desafio.login.protocolo;

import br.com.conexa.desafio.utils.ProtocolRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Protocolo de comunicação de entrada do serviço de login.
 * 
 * @author Angelo Vicente Filho
 *
 */
@Builder(setterPrefix = "com")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements ProtocolRequest {

	private String usuario;
	private String senha;
	
}
