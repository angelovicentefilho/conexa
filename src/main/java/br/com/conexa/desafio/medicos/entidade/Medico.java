package br.com.conexa.desafio.medicos.entidade;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.conexa.desafio.agendamento.entidade.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medico_id")
	@Basic(optional = false)
	private Long medicoId;
	
	@NotBlank(message = "O nome do médico é obrigatório")
	private String nome;
	
	@NotBlank(message = "A especialidade do médico é obrigatória")
	private String especialidade;
	
	@NotBlank(message = "Usuário é obrigatório")
	@Email(message = "O usuário deve ser um email válido")
	@Column(nullable = false, unique = true)
	private String usuario;
	
	@NotBlank(message = "Senha é obrigatória")
	@Column(nullable = false)
	private String senha;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "medicoId", fetch = FetchType.LAZY)
	private Set<Agendamento> agendamentos;
	
	private String token;
	
	
}
