package com.devops.dxc.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;
import com.devops.dxc.devops.service.Calculadora;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DevopsApplicationTests {

	@ParameterizedTest
	@CsvSource({
			"200000,100,0",
			"2000000,100,19",
			"2000000,1000,190",
			"6000000,10000,1900",
	})
	public void testImpuesto(long sueldo, long dxc, long expected) throws Exception {
		assertEquals(expected, Util.getImpuesto(sueldo, dxc));
	}

	@ParameterizedTest
	@CsvSource({
			"200000,100,199900",
			"2000000,2000000,0",
			"2000000,1000000,1000000",
	})
	public void testSaldo(long ahorro, long dxc, long expected) throws Exception {
		assertEquals(expected, Util.getSaldo(ahorro, dxc));
	}

	@ParameterizedTest
	@CsvSource({
			"1050000,1000000,30000,1050000", // ahorro <= 35 * uf -> ahorro
			"10000,1000000,30000,10000", // ahorro <= 35 * uf -> ahorro
			"1050000,1000000,30000,1050000", // ((ahorro * 0.1) <= 35 * uf && ahorro >= 35 * uf) -> 35 * uf
			"10500000,1000000,30000,1050000", // ((ahorro * 0.1) <= 35 * uf && ahorro >= 35 * uf) -> 35 * uf
			"10600000,1000000,30000,1060000", // (ahorro * 0.1)
			"1000000000,1000000,30000,4500000", // (150 * uf)
	})
	public void testDxc(long ahorro, long sueldo, int uf, long expected) throws Exception {
		assertEquals(expected, Util.getDxc(ahorro, sueldo, uf));
	}

	@ParameterizedTest
	@CsvSource({
			"100000000,2000000,30000,855000,95500000,4500000", // ahorro con impuestos
			"100000000,1400000,30000,0,95500000,4500000", // ahorro sin impuesto
			"100000,1400000,30000,0,0,100000", // ahorro total como dxc sin impuesto
			"100000,1600000,30000,19000,0,100000", // ahorro total como dxc con impuesto
	})
	public void testCalculadora(long ahorro, long sueldo, int uf, long expectedImpuesto, long expectedSaldo,
			long expectedDxc) throws Exception {
		UFProviderMock ufProvider = new UFProviderMock(uf);
		Calculadora calculadora = new Calculadora(ufProvider);
		Dxc dxc = calculadora.calcularDxC(new Date(), sueldo, ahorro);
		assertEquals(ahorro, dxc.getAhorro());
		assertEquals(expectedImpuesto, dxc.getImpuesto());
		assertEquals(expectedSaldo, dxc.getSaldo());
		assertEquals(sueldo, dxc.getSueldo());
		assertEquals(uf, dxc.getUf());
		assertEquals(expectedDxc, dxc.getDxc());
	}
}
