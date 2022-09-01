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
public class Billete {
    private String localizador;
    private int id_billeteRuta;
    private int id_operacion;
    private String fechaOperacion;

    public Billete(String localizador, int id_billeteRuta, int id_operacion, String fechaOperacion) {
        this.localizador = localizador;
        this.id_billeteRuta = id_billeteRuta;
        this.id_operacion = id_operacion;
        this.fechaOperacion = fechaOperacion;
    }

    public String getLocalizador() {
        return localizador;
    }

    public void setLocalizador(String localizador) {
        this.localizador = localizador;
    }

    public int getId_billeteRuta() {
        return id_billeteRuta;
    }

    public void setId_billeteRuta(int id_billeteRuta) {
        this.id_billeteRuta = id_billeteRuta;
    }

    public int getId_operacion() {
        return id_operacion;
    }

    public void setId_operacion(int id_operacion) {
        this.id_operacion = id_operacion;
    }

    public String getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }
    
}
