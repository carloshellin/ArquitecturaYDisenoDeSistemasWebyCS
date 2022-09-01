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
public class AcompañantesBillete {
    private int id_acomp;
    private String DNI_acomp;
    private String localizador_billete;

    public AcompañantesBillete(int id_acomp, String DNI_acomp, String localizador_billete) {
        this.id_acomp = id_acomp;
        this.DNI_acomp = DNI_acomp;
        this.localizador_billete = localizador_billete;
    }

    public int getId_acomp() {
        return id_acomp;
    }

    public void setId_acomp(int id_acomp) {
        this.id_acomp = id_acomp;
    }

    public String getDNI_acomp() {
        return DNI_acomp;
    }

    public void setDNI_acomp(String DNI_acomp) {
        this.DNI_acomp = DNI_acomp;
    }

    public String getLocalizador_billete() {
        return localizador_billete;
    }

    public void setLocalizador_billete(String localizador_billete) {
        this.localizador_billete = localizador_billete;
    }
    
}
