package com.practicafinal.ventabilletes.bbdd;

import java.util.Date;

public class Operacion
{
    private final int idOperacion;
    private final Date fecha;
    private final float descuento;
    private final int Cliente_idCliente;

    public Operacion(int idOperacion, Date fecha, float descuento,
            int Cliente_idCliente)
    {
        this.idOperacion = idOperacion;
        this.fecha = fecha;
        this.descuento = descuento;
        this.Cliente_idCliente = Cliente_idCliente;
    }
    
    public Operacion(Date fecha, float descuento,
            int Cliente_idCliente)
    {
        this.idOperacion = -1;
        this.fecha = fecha;
        this.descuento = descuento;
        this.Cliente_idCliente = Cliente_idCliente;
    }

    public int getIdOperacion()
    {
        return idOperacion;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public float getDescuento()
    {
        return descuento;
    }

    public int getCliente_idCliente()
    {
        return Cliente_idCliente;
    }
}
