package br.com.conexa.desafio.pacientes.protocolo;

import java.util.List;

import br.com.conexa.desafio.utils.ProtocolResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(setterPrefix = "com")
public class PacienteResponseList implements ProtocolResponse {

	List<PacienteResponse> pacientes;
	
}
