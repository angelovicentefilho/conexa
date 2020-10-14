package br.com.conexa.desafio.agendamento.negocio;

import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.com.conexa.desafio.agendamento.entidade.Agendamento;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoRequest;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoResponse;
import br.com.conexa.desafio.agendamento.repositorio.AgendamentoRepositorio;
import br.com.conexa.desafio.medicos.entidade.Medico;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional(transactionManager = "transactionManager")
class AgendamentoNegocioImplTest {

	@BeforeEach
	public void setUp() {
		Set<Agendamento> agendamentos = new HashSet<>();
		agendamentos.add(new Agendamento(1L, 1L, 1L, new Date()));
		agendamentos.add(new Agendamento(2L, 2L, 2L, new Date()));
	}

	@Mock
	private Medico medico;

	@Mock
	private AgendamentoRepositorio repositorio;

	@Autowired
	private AgendamentoNegocioImpl negocio;

	@Test
	void testObtemAgendamentosPorMedicoHoje() {
		when(medico.getMedicoId()).thenReturn(1L);
		Set<AgendamentoResponse> response = negocio.obtemAgendamentosPorMedicoHoje(medico);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(2, response.size());
	}

	@Test
	void testAgendar() {
		AgendamentoRequest request = AgendamentoRequest.builder()//
				.comMedicoId(1l)//
				.comIdPaciente(1l)//
				.comDataHoraAtendimento("2020-01-01 12:00:00")//
				.build();
		AgendamentoResponse response = negocio.agendar(request);
		Assertions.assertEquals(1L, response.getIdPaciente());
		Assertions.assertEquals("2020-01-01 12:00:00", response.getDataHoraAtendimento());
	}

}
