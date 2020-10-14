package br.com.conexa.desafio.pacientes.controle;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import br.com.conexa.desafio.ClassAbstrataTest;
import br.com.conexa.desafio.pacientes.protocolo.PacienteRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional(transactionManager = "transactionManager")
class PacienteControleTest extends ClassAbstrataTest {

	@WithMockUser(username = "augusto@email.com", password = "12374")
	@Test
	void obtemPacientes() throws Exception {

		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		mockMvc.perform(get("/pacientes")//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isOk());
	}

	@WithMockUser(username = "augusto@email.com")
	@Test
	void obtemPacientePorId() throws Exception {

		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		mockMvc.perform(get("/pacientes/1")//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isOk());
	}

	@Test
	void naoPodeAcessarPaciente() throws Exception {
		mockMvc.perform(get("/pacientes"))//
				.andExpect(status().is(401));
	}

	@WithMockUser(username = "augusto@email.com")
	@Test
	void temQueCadastrarPaciente() throws Exception {

		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		mockMvc.perform(
				post("/pacientes").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
						.content(json(PacienteRequest.builder().comCpf("teste").comIdade(10).comNome("nome")
								.comTelefone("Telefone").build())))//
				.andExpect(status().isOk());
	}

	@WithMockUser(username = "augusto@email.com")
	@Test
	void temQueAtualizarPaciente() throws Exception {

		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		mockMvc.perform(
				put("/pacientes").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
						.content(json(PacienteRequest.builder().comCpf("teste").comIdade(10).comNome("nome")
								.comTelefone("Telefone").build())))//
				.andExpect(status().isOk());
	}

	@WithMockUser(username = "augusto@email.com")
	@Test
	void temQueDeletarPaciente() throws Exception {
		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		mockMvc.perform(delete("/pacientes/4").header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@WithMockUser(username = "augusto@email.com")
	@Test
	void temQueDarUmaExcecao() throws Exception {
		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		Assertions.assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(get("/pacientes/15").header("Authorization", "Bearer " + token)
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		});
	}

}
