package br.com.conexa.desafio.seguranca;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.conexa.desafio.medicos.entidade.Medico;

public class MedicoSeguro implements UserDetails {

	private static final long serialVersionUID = 1L;


	private Medico medico;

	public MedicoSeguro(Medico medico) {
		this.medico = medico;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return medico.getSenha();
	}

	@Override
	public String getUsername() {
		return medico.getUsuario();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Medico getMedico() {
		return medico;
	}

}
