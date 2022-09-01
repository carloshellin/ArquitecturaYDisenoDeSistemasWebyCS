package com.practicafinal.ventabilletes.bbdd;

public class BilleteRuta
{
    private final int billeteRuta;
    private final float precio;
    private final float impuestos;
    private final int Ruta_idRuta;

    public BilleteRuta(int billeteRuta, float precio, float impuestos,
            int Ruta_idRuta)
    {
        this.billeteRuta = billeteRuta;
        this.precio = precio;
        this.impuestos = impuestos;
        this.Ruta_idRuta = Ruta_idRuta;
    }
    
    public BilleteRuta(float precio, float impuestos,
            int Ruta_idRuta)
    {
        this.billeteRuta = -1;
        this.precio = precio;
        this.impuestos = impuestos;
        this.Ruta_idRuta = Ruta_idRuta;
    }

    public int getBilleteRuta()
    {
        return billeteRuta;
    }

    public float getPrecio()
    {
        return precio;
    }

    public float getImpuestos()
    {
        return impuestos;
    }

    public int getRuta_idRuta()
    {
        return Ruta_idRuta;
    }
}
