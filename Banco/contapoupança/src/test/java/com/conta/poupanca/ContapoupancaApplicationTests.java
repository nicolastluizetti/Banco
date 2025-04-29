package com.conta.poupanca;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.conta.poupanca.model.Titular;

@SpringBootTest
class ContapoupancaApplicationTests {

	@Test
	public void TestarCadastrarContacomSucesso() {
		 Titular novo = new Titular();
		 novo.setNome("nicolas");
		 novo.setCpf(55498543);
		 novo.setTelefone((long) 90000);
		 assertThat(novo).isNotNull();
		
	}

}
