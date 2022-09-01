package com.practicafinal.ventabilletes.bbdd;

public class ClaseAvion
{
    private final int Clase_idClase;
    private final int Avion_idAvion;
    private final int numPlazas;

    public ClaseAvion(int Clase_idClase, int Avion_idAvion, int numPlazas)
    {
        this.Clase_idClase = Clase_idClase;
        this.Avion_idAvion = Avion_idAvion;
        this.numPlazas = numPlazas;
    }

    public int getClase_idClase()
    {
        return Clase_idClase;
    }

    public int getAvion_idAvion()
    {
        return Avion_idAvion;
    }

    public int getNumPlazas()
    {
        return numPlazas;
    }
}
