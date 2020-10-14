package br.com.conexa.desafio.login.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.conexa.desafio.login.protocolo.LoginRequest;
import br.com.conexa.desafio.login.protocolo.LoginResponse;
import br.com.conexa.desafio.login.servico.LoginServico;

/**
 * Camada de apresentação/controle, utilizada para dar endpoint ao login
 * 
 * @author Angelo Vicente Filho
 *
 */
@CrossOrigin
@RestController
public class LoginControle {

	@Autowired
	private LoginServico servico;
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		return servico.logar(request);
	}
	
	@GetMapping("/medicos/logout")
	public void logout() {
		servico.logout();
	}
	
}
