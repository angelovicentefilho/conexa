package br.com.conexa.desafio.medicos.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.conexa.desafio.medicos.entidade.Medico;
import br.com.conexa.desafio.medicos.repositorio.MedicoRepositorio;

/**
 * Regra de negocio para o serviço de médicos.
 * 
 * @author Angelo Vicente Filho
 *
 */

@Component
public class MedicoNegocioImpl implements MedicoNegocio {

	@Autowired
	private MedicoRepositorio repositorio;
	
	public Medico obtemMedicoPorUsuarioESenha(String usuario, String senha) {
		return repositorio.obtemMedicoPorUsuarioESenha(usuario, senha);
	}

	@Override
	public void login(Medico medico) {
		repositorio.saveAndFlush(medico);
	}

	@Override
	public void sair(Medico medico) {
		medico.setToken(null);
		repositorio.saveAndFlush(medico);
	}

	@Override
	public Medico obtemMedicoPeloUsuario(String usuario) {
		return repositorio.obtemMedicoPorUsuario(usuario);
	}

}
