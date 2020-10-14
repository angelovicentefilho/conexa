package br.com.conexa.desafio.login.controle;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import br.com.conexa.desafio.ClassAbstrataTest;
import br.com.conexa.desafio.medicos.entidade.Medico;
import br.com.conexa.desafio.seguranca.MedicoSeguro;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional(transactionManager = "transactionManager")
class LoginControleTest extends ClassAbstrataTest {

	
	
	@Test
	void testLogin() throws Exception {
		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		Assertions.assertNotNull(token);
	}

	@WithMockUser(username = "augusto@email.com", password = "12374")
	@Test
	void testLogout() throws Exception {
		Medico medico = new Medico();
		medico.setEspecialidade("especialidade");
		medico.setMedicoId(15L);
		medico.setNome("medico");
		medico.setUsuario("usuario@email.com");
		medico.setSenha("senha");
		MedicoSeguro seguro = new MedicoSeguro(medico);
		final String token = extractToken(login("augusto@email.com", "12374").andReturn());
		mockMvc.perform(get("/medicos/logout")//
				.with(user(seguro))
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isOk());
	}

}
