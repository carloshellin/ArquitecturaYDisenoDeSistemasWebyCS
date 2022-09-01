package com.practicafinal.ventabilletes.bbdd;

public class Cliente
{
    private final int idCliente;
    private final String correo;
    private final String contraseña;
    private final String telefono;
    private final String Persona_numID;

    public Cliente(int idCliente, String correo, String contraseña,
            String telefono, String Persona_numID)
    {
        this.idCliente = idCliente;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.Persona_numID = Persona_numID;
    }
    
    public Cliente(String correo, String contraseña,
            String telefono, String Persona_numID)
    {
        this.idCliente = -1;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.Persona_numID = Persona_numID;
    }

    public int getIdCliente()
    {
        return idCliente;
    }

    public String getCorreo()
    {
        return correo;
    }

    public String getContraseña()
    {
        return contraseña;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public String getPersona_numID()
    {
        return Persona_numID;
    }
}
