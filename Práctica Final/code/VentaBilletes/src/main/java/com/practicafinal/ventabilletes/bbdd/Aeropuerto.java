package com.practicafinal.ventabilletes.bbdd;

public class Aeropuerto
{
    private final int idAeropuerto;
    private final String nombre;
    private final String direccion;
    private final String ciudad;
    private final String pais;
    private final String telefono;

    public Aeropuerto(int idAeropuerto, String nombre, String direccion,
            String ciudad, String pais, String telefono)
    {
        this.idAeropuerto = idAeropuerto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.telefono = telefono;
    }
    
    public Aeropuerto(String nombre, String direccion,
            String ciudad, String pais, String telefono)
    {
        this.idAeropuerto = -1;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.telefono = telefono;
    }

    public int getIdAeropuerto()
    {
        return idAeropuerto;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public String getCiudad()
    {
        return ciudad;
    }

    public String getPais()
    {
        return pais;
    }

    public String getTelefono()
    {
        return telefono;
    }
}
