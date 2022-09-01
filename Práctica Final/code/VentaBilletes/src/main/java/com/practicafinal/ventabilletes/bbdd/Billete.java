package com.practicafinal.ventabilletes.bbdd;

import java.util.Date;

public class Billete
{
    private final String localizador;
    private final int BilleteRuta_idBilleteRuta;
    private final int Operacion_idOperacion;
    private final Date Operacion_fecha;

    public Billete(String localizador, int BilleteRuta_idBilleteRuta,
            int Operacion_idOperacion, Date Operacion_fecha)
    {
        this.localizador = localizador;
        this.BilleteRuta_idBilleteRuta = BilleteRuta_idBilleteRuta;
        this.Operacion_idOperacion = Operacion_idOperacion;
        this.Operacion_fecha = Operacion_fecha;
    }

    public String getLocalizador()
    {
        return localizador;
    }

    public int getBilleteRuta_idBilleteRuta()
    {
        return BilleteRuta_idBilleteRuta;
    }

    public int getOperacion_idOperacion()
    {
        return Operacion_idOperacion;
    }

    public Date getOperacion_fecha()
    {
        return Operacion_fecha;
    }
}
