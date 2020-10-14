package br.com.conexa.desafio.agendamento.repositorio;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.conexa.desafio.agendamento.entidade.Agendamento;

@Repository
public interface AgendamentoRepositorio extends JpaRepository<Agendamento, Long> {

	@Transactional(readOnly = true)
	@Query("SELECT a FROM Agendamento a WHERE date_format(a.dataHoraAtendimento, '%y-%m-%d') = date_format(now(), '%y-%m-%d') AND a.medicoId = :medicoId")
	Set<Agendamento> obtemAgendamentosPorMedicoHoje(@Param("medicoId") Long medicoId);
}
