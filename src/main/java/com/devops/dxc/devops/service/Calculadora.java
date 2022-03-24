package com.devops.dxc.devops.service;

import java.util.Date;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;

public class Calculadora {

    UFProvider ufProvider;

    public Calculadora(UFProvider ufProvider) {
        this.ufProvider = ufProvider;
    }

    public Dxc calcularDxC(Date dia, int sueldo, int ahorro) throws UFNoDisponibleException {
        int uf = this.ufProvider.getPorDia(dia);
        int dxc = Util.getDxc(ahorro, sueldo, uf);
        int impuesto = Util.getImpuesto(sueldo, dxc);
        int saldo = Util.getSaldo(ahorro, dxc, impuesto);
        Dxc response = new Dxc(sueldo, ahorro, uf, dxc, impuesto, saldo);
        return response;
    }
}