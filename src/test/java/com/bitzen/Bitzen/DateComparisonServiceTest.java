package com.bitzen.Bitzen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.bitzen.Bitzen.Service.PessoaService;

class DateComparisonServiceTest {

	@Test
    public void testIsDateAfterCurrentDate() {
        PessoaService pessoaService = new PessoaService();
        
        // Data futura
        LocalDate futureDate = LocalDate.of(2030, 3, 25);
        boolean resultFuture = pessoaService.checkDate(futureDate);
        assertTrue(resultFuture, "Espera-se que a data futura seja considerada posterior à data atual");

        // Data passada
        LocalDate pastDate = LocalDate.of(2000, 3, 25);
        boolean resultPast = pessoaService.checkDate(pastDate);
        assertFalse(resultPast, "Espera-se que a data passada não seja considerada posterior à data atual");
    }

}
