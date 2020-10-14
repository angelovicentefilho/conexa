package br.com.conexa.desafio.agendamento.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.conexa.desafio.medicos.entidade.Medico;
import br.com.conexa.desafio.pacientes.entidade.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "agendamentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Agendamento implements Serializable{

	private static final long serialVersionUID = -3844300758793971106L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "agendamento_id")
	private Long agendamentoId;
	
	@Column(name = "paciente_id")
	private Long pacienteId;
	
	@Column(name = "medico_id")
	private Long medicoId;
	
	@Column(name = "data_hora_atendimento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraAtendimento;

	
}
