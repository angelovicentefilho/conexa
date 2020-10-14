package br.com.conexa.desafio.pacientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conexa.desafio.pacientes.entidade.Paciente;

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

}
