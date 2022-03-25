package com.devops.dxc.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;
import com.devops.dxc.devops.service.Calculadora;
import com.devops.dxc.devops.service.UFProvider;

import org.junit.jupiter.api.Test;
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
	public void testImpuesto(int sueldo, int dxc, int expected) throws Exception {
		assertEquals(expected, Util.getImpuesto(sueldo, dxc));
	}

	@ParameterizedTest
	@CsvSource({
			"200000,100,0,199900",
			"2000000,2000000,0,0",
			"2000000,1000000,0,1000000",
	})
	public void testSaldo(int ahorro, int dxc, int impuesto, int expected) throws Exception {
		assertEquals(expected, Util.getSaldo(ahorro, dxc, impuesto));
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
	public void testDxc(int ahorro, int sueldo, int uf, int expected) throws Exception {
		assertEquals(expected, Util.getDxc(ahorro, sueldo, uf));
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
	public void testCalculadora(int ahorro, int sueldo, int uf, int expected) throws Exception {
		UFProviderMock ufProvider = new UFProviderMock(30000);
		Calculadora calculadora = new Calculadora(ufProvider);
		Dxc dxc = calculadora.calcularDxC(new Date(), ahorro, sueldo);
		// @todo algo
		assertEquals(expected, dxc.getAhorro());
		assertEquals(expected, dxc.getImpuesto());
		assertEquals(expected, dxc.getSaldo());
		assertEquals(expected, dxc.getSueldo());
		assertEquals(expected, dxc.getUf());
		assertEquals(expected, dxc.getDxc());
	}
}
