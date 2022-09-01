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
public class Acompañante {
    private String persona_numID;

    public Acompañante(String persona_numID) {
        this.persona_numID = persona_numID;
    }

    public String getPersona_numID() {
        return persona_numID;
    }

    public void setPersona_numID(String persona_numID) {
        this.persona_numID = persona_numID;
    }
    
    
}
