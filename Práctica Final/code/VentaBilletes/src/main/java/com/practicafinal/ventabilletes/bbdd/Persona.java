package com.practicafinal.ventabilletes.bbdd;

import java.text.SimpleDateFormat;
import java.text.Format;
import java.util.Date;
import java.text.ParseException;

public class Persona
{
    private final String numID;
    private final String tipoIdentificacion;
    private final String nombre;
    private final String apellido1;
    private final String apellido2;
    private final Date fechaNacimiento;
    private final String fechaNacString;
    private final String sexo;

    public Persona(String numID, String tipoIdentificacion, String nombre,
            String apellido1, String apellido2, String fechaNacString,
            String sexo) throws ParseException
    {
        this.numID = numID;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacString = fechaNacString;
        this.sexo = sexo;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");     
        this.fechaNacimiento = formato.parse(fechaNacString);
       
    }

    public Persona(String numID, String tipoIdentificacion, String nombre, String apellido1, String apellido2, Date fechaNacimiento, String sexo) {
        this.numID = numID;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.fechaNacString = formatter.format(fechaNacimiento);
    }
    
    

    public String getNumID()
    {
        return numID;
    }

    public String getTipoIdentificacion()
    {
        return tipoIdentificacion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getApellido1()
    {
        return apellido1;
    }

    public String getApellido2()
    {
        return apellido2;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaNacString() {
        return fechaNacString;
    }

    
    

    

    public String getSexo()
    {
        return sexo;
    }
}
