package com.practicafinal.ventabilletes.bbdd;

public class Avion
{
    private final int idAvion;
    private final String modelo;
    private final int numPlazas;

    public Avion(int idAvion, String modelo, int numPlazas)
    {
        this.idAvion = idAvion;
        this.modelo = modelo;
        this.numPlazas = numPlazas;
    }
    
    public Avion(String modelo, int numPlazas)
    {
        this.idAvion = -1;
        this.modelo = modelo;
        this.numPlazas = numPlazas;
    }

    public int getIdAvion()
    {
        return idAvion;
    }

    public String getModelo()
    {
        return modelo;
    }

    public int getNumPlazas()
    {
        return numPlazas;
    }
}
