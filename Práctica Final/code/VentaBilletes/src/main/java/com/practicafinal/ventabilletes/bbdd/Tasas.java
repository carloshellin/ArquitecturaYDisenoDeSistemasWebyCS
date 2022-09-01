package com.practicafinal.ventabilletes.bbdd;

public class Tasas
{
    private final int idTasas;
    private final float gestion;
    private final float seguridad;
    private final float combustible;
    private final int Aeropuerto_idAeropuerto;

    public Tasas(int idTasas, float gestion, float seguridad,
            float combustible, int Aeropuerto_idAeropuerto)
    {
        this.idTasas = idTasas;
        this.gestion = gestion;
        this.seguridad = seguridad;
        this.combustible = combustible;
        this.Aeropuerto_idAeropuerto = Aeropuerto_idAeropuerto;
    }
    
    public Tasas(float gestion, float seguridad,
            float combustible, int Aeropuerto_idAeropuerto)
    {
        this.idTasas = -1;
        this.gestion = gestion;
        this.seguridad = seguridad;
        this.combustible = combustible;
        this.Aeropuerto_idAeropuerto = Aeropuerto_idAeropuerto;
    }

    public int getIdTasas()
    {
        return idTasas;
    }

    public float getGestion()
    {
        return gestion;
    }

    public float getSeguridad()
    {
        return seguridad;
    }

    public float getCombustible()
    {
        return combustible;
    }

    public int getAeropuerto_idAeropuerto()
    {
        return Aeropuerto_idAeropuerto;
    }
}
