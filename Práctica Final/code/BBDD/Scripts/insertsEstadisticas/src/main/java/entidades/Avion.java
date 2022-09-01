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
public class Avion {
    private int id_avion;
    private String modelo;
    private int numPasajeros;

    public Avion(int id_avion, String modelo, int numPasajeros) {
        this.id_avion = id_avion;
        this.modelo = modelo;
        this.numPasajeros = numPasajeros;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNumPasajeros() {
        return numPasajeros;
    }

    public void setNumPasajeros(int numPasajeros) {
        this.numPasajeros = numPasajeros;
    }
    
}
