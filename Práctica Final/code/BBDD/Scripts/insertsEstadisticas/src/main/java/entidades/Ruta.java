/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author franchu7
 */
public class Ruta {
    private String fecha;
    private int duracion;
    private int avion_id;
    private int origen_id;
    private int destino_id;
    private int cantidadPasajeros = 0;

    public Ruta() {
    }

    public Ruta(String fecha, int duracion, int avion_id, int origen_id, int destino_id) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.avion_id = avion_id;
        this.origen_id = origen_id;
        this.destino_id = destino_id;
    }

    public void incPasajerosAvion() {
        cantidadPasajeros++;
    }
    
    public void decPasajerosAvion() {
        cantidadPasajeros--;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getAvion_id() {
        return avion_id;
    }

    public void setAvion_id(int avion_id) {
        this.avion_id = avion_id;
    }

    public int getOrigen_id() {
        return origen_id;
    }

    public void setOrigen_id(int origen_id) {
        this.origen_id = origen_id;
    }

    public int getDestino_id() {
        return destino_id;
    }

    public void setDestino_id(int destino_id) {
        this.destino_id = destino_id;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }
    
    
    
}
