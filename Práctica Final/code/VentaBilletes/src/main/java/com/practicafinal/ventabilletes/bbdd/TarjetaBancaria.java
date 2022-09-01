package com.practicafinal.ventabilletes.bbdd;

import java.math.BigInteger;

public class TarjetaBancaria
{
    private final BigInteger numTarjeta;
    private final int mesCaducidad;
    private final int añoCaducidad;
    private final int CVV;
    private final int Cliente_idCliente;

    public TarjetaBancaria(BigInteger numTarjeta, int mesCaducidad, int añoCaducidad,
            int CVV, int Cliente_idCliente)
    {
        this.numTarjeta = numTarjeta;
        this.mesCaducidad = mesCaducidad;
        this.añoCaducidad = añoCaducidad;
        this.CVV = CVV;
        this.Cliente_idCliente = Cliente_idCliente;
    }

    public BigInteger getNumTarjeta()
    {
        return numTarjeta;
    }

    public int getMesCaducidad()
    {
        return mesCaducidad;
    }

    public int getAñoCaducidad()
    {
        return añoCaducidad;
    }

    public int getCVV()
    {
        return CVV;
    }

    public int getCliente_idCliente()
    {
        return Cliente_idCliente;
    }
}
