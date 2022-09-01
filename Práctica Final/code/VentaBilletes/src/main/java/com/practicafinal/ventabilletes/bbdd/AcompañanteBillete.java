package com.practicafinal.ventabilletes.bbdd;

public class AcompañanteBillete
{
    private final int idAcompañenteBillete;
    private final int Acompañante_idAcompañente;
    private final String Acompañente_Persona_numID;
    private final String Billete_localizador;

    public AcompañanteBillete(int idAcompañenteBillete,
            int Acompañante_idAcompañente, String Acompañente_Persona_numID,
            String Billete_localizador)
    {
        this.idAcompañenteBillete = idAcompañenteBillete;
        this.Acompañante_idAcompañente = Acompañante_idAcompañente;
        this.Acompañente_Persona_numID = Acompañente_Persona_numID;
        this.Billete_localizador = Billete_localizador;
    }
    
    public AcompañanteBillete(int Acompañante_idAcompañente,
            String Acompañente_Persona_numID, String Billete_localizador)
    {
        this.idAcompañenteBillete = -1;
        this.Acompañante_idAcompañente = Acompañante_idAcompañente;
        this.Acompañente_Persona_numID = Acompañente_Persona_numID;
        this.Billete_localizador = Billete_localizador;
    }

    public int getIdAcompañenteBillete()
    {
        return idAcompañenteBillete;
    }

    public int getAcompañante_idAcompañente()
    {
        return Acompañante_idAcompañente;
    }

    public String getAcompañente_Persona_numID()
    {
        return Acompañente_Persona_numID;
    }

    public String getBillete_localizador()
    {
        return Billete_localizador;
    }
}
