package com.practicafinal.ventabilletes.bbdd;

public class Clase
{
    private final int idClase;
    private final String nombre;
    private final String descripcion;

    public Clase(int idClase, String nombre, String descripcion)
    {
        this.idClase = idClase;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public Clase(String nombre, String descripcion)
    {
        this.idClase = -1;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdClase()
    {
        return idClase;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }
}
