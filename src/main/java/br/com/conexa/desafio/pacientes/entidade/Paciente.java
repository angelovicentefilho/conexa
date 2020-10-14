package br.com.conexa.desafio.pacientes.entidade;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.com.conexa.desafio.agendamento.entidade.Agendamento;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pacientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Paciente implements Serializable {
	 
	private static final long serialVersionUID = -8825279053181720493L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "paciente_id")
	private Long pacienteId;
	
	@Size(max = 255)
	@Column(name = "id_paciente")
	private String uuid;
	
	private String nome;
	
	private String cpf;
	
	private Integer idade;
	
	private String telefone;
	
    @OneToMany(mappedBy = "pacienteId", fetch = FetchType.EAGER)
    private Set<Agendamento> agendamentos;

}
