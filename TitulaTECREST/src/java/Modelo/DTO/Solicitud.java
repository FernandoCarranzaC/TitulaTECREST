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
public class Solicitud {
    private int idSolicitud;
    private String fechaRegistro;
    private String fechaAtencion;
    private String tituloProyecto;
    private String estatus;
    private Opcion opcion;
    private Administrativo administrativo;
    private Alumno alumno;
    private String tipoUsuario;

    /**
     * @return the idSolicitud
     */
    public int getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * @param idSolicitud the idSolicitud to set
     */
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    /**
     * @return the fechaRegistro
     */
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaAtencion
     */
    public String getFechaAtencion() {
        return fechaAtencion;
    }

    /**
     * @param fechaAtencion the fechaAtencion to set
     */
    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    /**
     * @return the tituloProyecto
     */
    public String getTituloProyecto() {
        return tituloProyecto;
    }

    /**
     * @param tituloProyecto the tituloProyecto to set
     */
    public void setTituloProyecto(String tituloProyecto) {
        this.tituloProyecto = tituloProyecto;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the opcion
     */
    public Opcion getOpcion() {
        return opcion;
    }

    /**
     * @param opcion the opcion to set
     */
    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    /**
     * @return the administrativo
     */
    public Administrativo getAdministrativo() {
        return administrativo;
    }

    /**
     * @param administrativo the administrativo to set
     */
    public void setAdministrativo(Administrativo administrativo) {
        this.administrativo = administrativo;
    }

    /**
     * @return the alumno
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
}
