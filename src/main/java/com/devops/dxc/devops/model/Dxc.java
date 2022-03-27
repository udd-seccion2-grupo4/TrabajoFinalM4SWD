package com.devops.dxc.devops.model;

import java.io.Serializable;

public class Dxc implements Serializable {

	private long dxc;
	private long sueldo;
	private long ahorro;
	private int uf;

	public Dxc(long sueldo, long ahorro, int uf, long dxc) {
		this.sueldo = sueldo;
		this.ahorro = ahorro;
		this.uf = uf;
		this.dxc = dxc;
	}

	public int getUf() {
		return uf;
	}

	public long getDxc() {
		return this.dxc;
	}

	public long getSueldo() {
		return sueldo;
	}

	public long getAhorro() {
		return ahorro;
	}

}
