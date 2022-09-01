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
public class Tarjeta_Bancaria {
    private long numTarjeta;
    private int mes_caducidad;
    private int anno_caducidad;
    private int cvv;
    private int id_cliente;

    public Tarjeta_Bancaria() {
    }

    public Tarjeta_Bancaria(long numTarjeta, int mes_caducidad, int anno_caducidad, int cvv, int id_cliente) {
        this.numTarjeta = numTarjeta;
        this.mes_caducidad = mes_caducidad;
        this.anno_caducidad = anno_caducidad;
        this.cvv = cvv;
        this.id_cliente = id_cliente;
    }

    public long getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(long numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public int getMes_caducidad() {
        return mes_caducidad;
    }

    public void setMes_caducidad(int mes_caducidad) {
        this.mes_caducidad = mes_caducidad;
    }

    public int getAnno_caducidad() {
        return anno_caducidad;
    }

    public void setAnno_caducidad(int anno_caducidad) {
        this.anno_caducidad = anno_caducidad;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
}
