package br.com.conexa.desafio.login.negocio;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.conexa.desafio.login.servico.LoginServico;

/**
 * Esta interface é utilizada para acesso interno.
 * 
 * @author Angelo Vicente Filho
 *
 */
public interface LoginNegocio extends LoginServico, UserDetailsService {

}
