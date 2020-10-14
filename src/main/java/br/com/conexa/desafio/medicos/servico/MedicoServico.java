package br.com.conexa.desafio.medicos.servico;

import org.springframework.stereotype.Service;

import br.com.conexa.desafio.medicos.entidade.Medico;

/**
 * Interface utilizada para dar acesso externo aos médicos.
 *  
 * @author Angelo Vicente Filho
 *
 */
@Service
public interface MedicoServico {

	public Medico obtemMedicoPorUsuarioESenha(String usuario, String senha);

}
