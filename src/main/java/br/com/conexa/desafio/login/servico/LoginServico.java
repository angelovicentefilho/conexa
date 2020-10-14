package br.com.conexa.desafio.login.servico;

import org.springframework.stereotype.Service;

import br.com.conexa.desafio.login.protocolo.LoginRequest;
import br.com.conexa.desafio.login.protocolo.LoginResponse;

/**
 * Esta interface é utilizada para acesso externo.
 * 
 * @author Angelo Vicente
 */
@Service
public interface LoginServico {

	LoginResponse logar(LoginRequest request);
	
	void logout();
	
}
