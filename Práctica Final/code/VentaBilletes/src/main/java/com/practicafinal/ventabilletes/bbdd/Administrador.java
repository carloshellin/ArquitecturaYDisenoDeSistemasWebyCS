package com.practicafinal.ventabilletes.bbdd;

public class Administrador
{
    private final int idAdministrador;
    private final String correo;
    private final String contraseña;
    private final String Persona_numID;

    public Administrador(int idAdministrador, String correo,
            String contraseña, String Persona_numID)
    {
        this.idAdministrador = idAdministrador;
        this.correo = correo;
        this.contraseña = contraseña;
        this.Persona_numID = Persona_numID;
    }

    public Administrador(String correo,
            String contraseña, String Persona_numID)
    {
        this.idAdministrador = -1;
        this.correo = correo;
        this.contraseña = contraseña;
        this.Persona_numID = Persona_numID;
    }
        
    public int getIdAdministrador()
    {
        return idAdministrador;
    }

    public String getCorreo()
    {
        return correo;
    }

    public String getContraseña()
    {
        return contraseña;
    }

    public String getPersona_numID()
    {
        return Persona_numID;
    }
}
