package br.com.conexa.desafio.login.negocio;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.com.conexa.desafio.login.protocolo.LoginRequest;
import br.com.conexa.desafio.login.protocolo.LoginResponse;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional(transactionManager = "transactionManager")
class LoginNegocioImplTest {
	
	@Mock
	private LoginRequest request;
	
	
	@InjectMocks
	@Autowired
	private LoginNegocioImpl negocio;
	
	@BeforeEach
	public void setUp() {
	}

	@Test
	void testLogar() {
		when(request.getUsuario()).thenReturn("augusto@email.com");
		when(request.getSenha()).thenReturn("12374");
		LoginResponse response = negocio.logar(request);
		Assertions.assertNotNull(response.getToken());
	}

}
