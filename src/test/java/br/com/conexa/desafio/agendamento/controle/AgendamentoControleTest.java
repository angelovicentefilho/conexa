package br.com.conexa.desafio.agendamento.controle;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import br.com.conexa.desafio.ClassAbstrataTest;
import br.com.conexa.desafio.agendamento.protocolo.AgendamentoRequest;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional(transactionManager = "transactionManager")
class AgendamentoControleTest extends ClassAbstrataTest {

	@WithMockUser(username = "augusto@email.com")
	@Test
	void testAgendarCorreto() throws Exception {

		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		mockMvc.perform(post("/agendamento")//
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json(AgendamentoRequest.builder()//
						.comDataHoraAtendimento("2010-01-01 12:12:12")//
						.comIdPaciente(1L)//
						.comMedicoId(1L)//
						.build())))//
				.andExpect(status().isOk());
	}
	
	@WithMockUser(username = "augusto@email.com")
	@Test
	void testAgendarSemCorpo() throws Exception {

		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		mockMvc.perform(post("/agendamento")//
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	

	@WithMockUser(username = "augusto@email.com")
	@Test
	void testAgendarIncorreto() throws Exception {

		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		mockMvc.perform(post("/agendamentos")//
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json(AgendamentoRequest.builder()//
						.comDataHoraAtendimento("2010-01-01 12:12:12")//
						.comIdPaciente(1L)//
						.comMedicoId(1L)//
						.build())))//
				.andExpect(status().isNotFound());
	}

}
