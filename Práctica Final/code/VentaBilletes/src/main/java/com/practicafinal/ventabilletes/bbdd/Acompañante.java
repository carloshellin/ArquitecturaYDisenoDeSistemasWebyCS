package com.practicafinal.ventabilletes.bbdd;

public class Acompañante
{
    private final int idAcompañante;
    private final String Persona_numID;

    public Acompañante(int idAcompañante, String Persona_numID)
    {
        this.idAcompañante = idAcompañante;
        this.Persona_numID = Persona_numID;
    }

    public int getIdAcompañante()
    {
        return idAcompañante;
    }

    public String getPersona_numID()
    {
        return Persona_numID;
    }
}
