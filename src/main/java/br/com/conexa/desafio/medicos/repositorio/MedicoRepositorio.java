package br.com.conexa.desafio.medicos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.conexa.desafio.medicos.entidade.Medico;

@Repository
public interface MedicoRepositorio extends JpaRepository<Medico, Long> {

	@Transactional(readOnly = true)
	@Query("SELECT m FROM Medico m WHERE m.usuario = :usuario AND m.senha = :senha")
	Medico obtemMedicoPorUsuarioESenha(@Param("usuario") String usuario, @Param("senha") String senha);

	@Transactional(readOnly = true)
	@Query("SELECT m FROM Medico m WHERE m.usuario = :usuario")
	Medico obtemMedicoPorUsuario(@Param("usuario") String usuario);

}
