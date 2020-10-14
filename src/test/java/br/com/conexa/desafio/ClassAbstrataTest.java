package br.com.conexa.desafio;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import br.com.conexa.desafio.login.protocolo.LoginRequest;

@SpringBootTest
public class ClassAbstrataTest {
	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext)//
				.apply(springSecurity())//
				.build();
	}

	protected String json(Object o) throws IOException {
		return mapper.writeValueAsString(o);
	}

	protected ResultActions login(String usuario, String senha) throws Exception {
		final LoginRequest auth = new LoginRequest();
		auth.setUsuario(usuario);
		auth.setSenha(senha);
		return mockMvc.perform(post("/login").content(json(auth)).contentType(MediaType.APPLICATION_JSON));
	}

	protected String extractToken(MvcResult result) throws UnsupportedEncodingException {
		return JsonPath.read(result.getResponse().getContentAsString(), "$.token");
	}

}
