package com.devops.dxc.devops.model;

import java.io.Serializable;

public class Dxc implements Serializable {

	private int dxc;
	private int saldo;
	private int impuesto;
	private int sueldo;
	private int ahorro;
	private int uf;

	public Dxc(int sueldo, int ahorro, int uf, int dxc, int impuesto, int saldo) {
		this.sueldo = sueldo;
		this.ahorro = ahorro;
		this.uf = uf;
		this.dxc = dxc;
		this.saldo = saldo;
	}

	public int getUf() {
		return uf;
	}

	public int getDxc() {
		return this.dxc;
	}

	public int getSaldo() {
		return saldo;
	}

	public int getImpuesto() {
		return impuesto;
	}

	public int getSueldo() {
		return sueldo;
	}

	public int getAhorro() {
		return ahorro;
	}

}
