package com.devops.dxc.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.devops.dxc.devops.model.Util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DevopsApplicationTests {

	@ParameterizedTest
	@CsvSource({
		"30000,200000,100,0",
		"30000,2000000,100,19",
		"30000,2000000,1000,190",
		"30000,6000000,1000,190",
	})
	public void testImpuesto(int uf, int sueldo, int dxc, int expected) throws Exception {
		assertEquals(expected, Util.getImpuesto(sueldo, dxc));
	}

	@Test
	public void testSaldoAhorroSinImpuestos() throws Exception {
		assertEquals(90, Util.getSaldo(100, 10, 0));
	}

	@Test
	public void testSaldoAhorroConImpuestos() throws Exception {
		assertEquals(881, Util.getSaldo(1000, 100, 19));
	}
}
