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
public class Alumno {
    private int idAlumno;
    private String noControl;
    private String nombre;
    private Carrera carrera;

    /**
     * @return the idAlumno
     */
    public int getIdAlumno() {
        return idAlumno;
    }

    /**
     * @param idAlumno the idAlumno to set
     */
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    /**
     * @return the noControl
     */
    public String getNoControl() {
        return noControl;
    }

    /**
     * @param noControl the noControl to set
     */
    public void setNoControl(String noControl) {
        this.noControl = noControl;
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

    /**
     * @return the carrera
     */
    public Carrera getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    
}
