package com.devops.dxc.devops;

import java.util.Date;

import com.devops.dxc.devops.service.UFNoDisponibleException;
import com.devops.dxc.devops.service.UFProvider;

public class UFProviderMock implements UFProvider {

    private int uf;

    public UFProviderMock(int uf) {
        this.uf = uf;
    }

    public int getUf() {
        return uf;
    }

    @Override
    public int getPorDia(Date dia) throws UFNoDisponibleException {
        return uf;
    }

}
