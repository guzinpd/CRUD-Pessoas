package com.bitzen.Bitzen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bitzen.Bitzen.Service.PessoaService;

class CpfValidTest {

	@Test
	void test() {
		PessoaService pessoaService = new PessoaService();
		boolean resultValid = pessoaService.checkCPF("094.899.016-36");
		assertTrue(resultValid);
		

		boolean resultInvalid = pessoaService.checkCPF("094.899.0-36");
		assertFalse(resultInvalid);
	}

}
