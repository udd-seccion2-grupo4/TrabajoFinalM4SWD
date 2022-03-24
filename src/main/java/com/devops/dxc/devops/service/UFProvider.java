package com.devops.dxc.devops.service;

import java.util.Date;

public interface UFProvider {
    public int getPorDia(Date dia) throws UFNoDisponibleException;
}
