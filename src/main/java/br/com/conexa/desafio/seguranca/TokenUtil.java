package br.com.conexa.desafio.seguranca;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;
	public static final long VALIDADE = 5 * 60 * 60;

	private String senhaToken = "MinhaSenhaDoToken";

	public String getMedicoDoToken(String token) {
		return getDadosDoToken(token, Claims::getSubject);
	}

	public Date getDataDeExpiracaoDoToken(String token) {
		return getDadosDoToken(token, Claims::getExpiration);
	}

	public <T> T getDadosDoToken(String token, Function<Claims, T> resolver) {
		final Claims claims = getTodosDadosToken(token);
		return resolver.apply(claims);

	}

	private Claims getTodosDadosToken(String token) {
		return Jwts.parser().setSigningKey(senhaToken).parseClaimsJws(token).getBody();
	}

	private Boolean estaExpirado(String token) {
		final Date expiracao = getDataDeExpiracaoDoToken(token);
		return expiracao.before(new Date());
	}

	public String gerarToken(UserDetails medicoSeguro) {
		Map<String, Object> claims = new HashMap<>();
		return criarToken(claims, medicoSeguro.getUsername());
	}

	private String criarToken(Map<String, Object> claims, String usuario) {
		return Jwts.builder().setClaims(claims).setSubject(usuario).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + VALIDADE * 1000))
				.signWith(SignatureAlgorithm.HS512, senhaToken).compact();
	}

	public Boolean validarToken(String token, UserDetails medicoSeguro) {
		final String usuario = getMedicoDoToken(token);
		return (usuario.equals(medicoSeguro.getUsername()) && !estaExpirado(token));
	}
}