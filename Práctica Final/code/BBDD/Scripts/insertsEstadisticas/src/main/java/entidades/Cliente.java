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
public class Cliente {
    private String correo;
    private String contraseña;
    private String telefono;
    private String persona_numID;

    public Cliente() {
    }

    public Cliente(String correo, String contraseña, String persona_numID) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.persona_numID = persona_numID;
    }

    public Cliente(String correo, String contraseña, String telefono, String persona_numID) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.persona_numID = persona_numID;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPersona_numID() {
        return persona_numID;
    }

    public void setPersona_numID(String persona_numID) {
        this.persona_numID = persona_numID;
    }
    
    
}
