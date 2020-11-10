/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DTO;

/**
 *
 * @author fcarr
 */
public class Administrativo {
    private int idAdministrativo;
    private String nombre;

    /**
     * @return the idAdministrativo
     */
    public int getIdAdministrativo() {
        return idAdministrativo;
    }

    /**
     * @param idAdministrativo the idAdministrativo to set
     */
    public void setIdAdministrativo(int idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
