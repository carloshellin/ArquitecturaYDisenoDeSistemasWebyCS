package com.practicafinal.ventabilletes.bbdd;

import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

public class Ruta
{
    private final int idRuta;
    private final Date fecha;
    private final int duraction;
    private final int Avion_idAvion;
    private final int origen;
    private final int destino;
    private final String fechaString;

    public Ruta(int idRuta, Date fecha, int duraction, int Avion_idAvion,
            int origen, int destino)
    {
        this.idRuta = idRuta;
        this.fecha = fecha;
        this.duraction = duraction;
        this.Avion_idAvion = Avion_idAvion;
        this.origen = origen;
        this.destino = destino;
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(fecha);
        this.fechaString = s;
    }
    
    
    
    public Ruta(Date fecha, int duraction, int Avion_idAvion,
            int origen, int destino)
    {
        this.idRuta = -1;
        this.fecha = fecha;
        this.duraction = duraction;
        this.Avion_idAvion = Avion_idAvion;
        this.origen = origen;
        this.destino = destino;
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(fecha);
        this.fechaString = s;
    }

    public Ruta(Date fecha, int duraction, int Avion_idAvion, int origen, int destino, String fechaString) {
        this.idRuta = -1;
        this.fecha = fecha;
        this.duraction = duraction;
        this.Avion_idAvion = Avion_idAvion;
        this.origen = origen;
        this.destino = destino;
        this.fechaString = fechaString;
    }

    


    public int getIdRuta()
    {
        return idRuta;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public int getDuraction()
    {
        return duraction;
    }

    public int getAvion_idAvion()
    {
        return Avion_idAvion;
    }

    public int getOrigen()
    {
        return origen;
    }

    public int getDestino()
    {
        return destino;
    }

    public String getFechaString() {
        return fechaString;
    }
    
    
}
