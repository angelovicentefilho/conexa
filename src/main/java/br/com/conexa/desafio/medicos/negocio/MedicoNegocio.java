package br.com.conexa.desafio.medicos.negocio;

import br.com.conexa.desafio.medicos.entidade.Medico;
import br.com.conexa.desafio.medicos.servico.MedicoServico;

public interface MedicoNegocio extends MedicoServico {
	
	public Medico obtemMedicoPeloUsuario(String usuario);
	
	public void login(Medico medico);
	
	public void sair(Medico medico);
}
