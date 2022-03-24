package com.devops.dxc.devops.rest;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.ErrorResponse;
import com.devops.dxc.devops.service.Calculadora;
import com.devops.dxc.devops.service.UFNoDisponibleException;
import com.devops.dxc.devops.service.UFProvider;
import com.devops.dxc.devops.service.UFProviderMiIndicador;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping(path = "/rest/msdxc")
public class RestData {

	private final static Logger LOGGER = Logger.getLogger("devops.subnivel.Control");

	@GetMapping(path = "/dxc", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Dxc getData(
			@RequestParam(name = "sueldo") int sueldo,
			@RequestParam(name = "ahorro") int ahorro) throws UFNoDisponibleException {

		LOGGER.log(Level.INFO, "<Trabajo DevOps - DXC> <Consultado Diez por ciento>");

		Date dia = new Date();
		UFProvider ufProvider = new UFProviderMiIndicador();
		Calculadora calculadora = new Calculadora(ufProvider);
		Dxc response;
		response = calculadora.calcularDxC(dia, sueldo, ahorro);
		return response;
	}
}