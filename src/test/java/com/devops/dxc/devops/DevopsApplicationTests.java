package com.devops.dxc.devops;

import static org.junit.jupiter.api.Assertions.*;

import com.devops.dxc.devops.model.Util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevopsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testUF() throws Exception {
		int uf = Util.getUf();
		assertTrue(uf > 0);
	}

	@Test
	public void testImpuestoSueldoBajo() throws Exception {
		assertEquals(0, Util.getImpuesto(200_000, 100));
	}

	@Test
	public void testImpuestoSueldoAlto() throws Exception {
		assertEquals(19, Util.getImpuesto(6_000_000, 100));
	}

	@Test
	public void testImpuestoSueldoLimite() throws Exception {
		assertEquals(19, Util.getImpuesto(1_500_000, 100));
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
