package br.com.conexa.desafio.login.negocio;

import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.conexa.desafio.agendamento.negocio.AgendamentoNegocio;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoResponse;
import br.com.conexa.desafio.login.protocolo.LoginRequest;
import br.com.conexa.desafio.login.protocolo.LoginResponse;
import br.com.conexa.desafio.medicos.entidade.Medico;
import br.com.conexa.desafio.medicos.negocio.MedicoNegocio;
import br.com.conexa.desafio.seguranca.MedicoSeguro;
import br.com.conexa.desafio.seguranca.TokenUtil;

/**
 * Classe que implementa a regra de negocio do login
 * 
 * @author Angelo Vicente Filho
 *
 */
@Component
public class LoginNegocioImpl implements LoginNegocio {

	@Autowired
	private MedicoNegocio medicoNegocio;

	@Autowired
	private AgendamentoNegocio agendamentoNegocio;

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public LoginResponse logar(LoginRequest request) {
		autenticar(request.getUsuario(), request.getSenha());
		MedicoSeguro medicoSeguro = (MedicoSeguro) loadUserByUsername(request.getUsuario());
		Medico medico = medicoSeguro.getMedico();
		final String token = tokenUtil.gerarToken(medicoSeguro);
		Set<AgendamentoResponse> agendamentos = agendamentoNegocio.obtemAgendamentosPorMedicoHoje(medico);
		medico.setToken(token);
		LoginResponse response = LoginResponse.builder()//
				.comToken(token)//
				.comNome(medico.getNome())//
				.comEspecialidade(medico.getEspecialidade())//
				.comAgendamentos(agendamentos)//
				.build();
		medicoNegocio.login(medico);
		return response;
	}

	private void autenticar(String usuario, String senha) {
		try {
			Medico medico = medicoNegocio.obtemMedicoPorUsuarioESenha(usuario, senha);
			if (Objects.nonNull(medico)) {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario, senha));
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void logout() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MedicoSeguro seguro = (MedicoSeguro) auth.getPrincipal();
		medicoNegocio.sair(seguro.getMedico());
	}

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Medico medico = medicoNegocio.obtemMedicoPeloUsuario(usuario);
		if (Objects.nonNull(medico)) {
			return new MedicoSeguro(medico);
		}
		throw new UsernameNotFoundException(usuario);
	}

}
