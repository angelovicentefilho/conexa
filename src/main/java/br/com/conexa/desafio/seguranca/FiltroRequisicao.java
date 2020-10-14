package br.com.conexa.desafio.seguranca;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.conexa.desafio.login.negocio.LoginNegocio;

@Component
public class FiltroRequisicao extends OncePerRequestFilter {

	@Autowired
	private LoginNegocio loginNegocio;

	@Autowired
	private TokenUtil tokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String header = request.getHeader("Authorization");
		String usuario = null;
		String token = null;

		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			usuario = tokenUtil.getMedicoDoToken(token);
		}

		if (temUsuarioSemContexto(usuario)) {
			MedicoSeguro medicoSeguro = (MedicoSeguro) this.loginNegocio.loadUserByUsername(usuario);
			if (tokenValidoPertenceAoMedico(token, medicoSeguro)) {
				if (tokensIguais(token, medicoSeguro)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							medicoSeguro, null, medicoSeguro.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				filterChain.doFilter(request, response);
			}
		}

		filterChain.doFilter(request, response);
	}

	private boolean tokensIguais(String token, MedicoSeguro medicoSeguro) {
		return medicoSeguro.getMedico().getToken().equalsIgnoreCase(token);
	}

	private boolean tokenValidoPertenceAoMedico(String token, MedicoSeguro medicoSeguro) {
		return tokenUtil.validarToken(token, medicoSeguro) && medicoSeguro.getMedico().getToken() != null;
	}

	private boolean temUsuarioSemContexto(String usuario) {
		return usuario != null && SecurityContextHolder.getContext().getAuthentication() == null;
	}

}
