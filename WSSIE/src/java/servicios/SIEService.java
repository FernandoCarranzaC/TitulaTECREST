/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modelo.DAO.AlumnoDAO;
import modelo.DAO.DocenteDAO;
import modelo.DTO.Alumno;
import modelo.DTO.Docente;

/**
 *
 * @author roberto
 */
@WebService(serviceName = "SIEService",targetNamespace = "http://com.itesz.soa")
public class SIEService {

    /**
     * Web service operation
     */
    DocenteDAO docenteDAO;
    AlumnoDAO alumnoDAO;
    public SIEService(){
        docenteDAO=new DocenteDAO();
        alumnoDAO=new AlumnoDAO();
    }
    
    @WebMethod(operationName = "consultarDocente")
    public Docente consultarDocente(@WebParam(name = "noDocente") int noDocente) {
        return docenteDAO.consultaIndividual(noDocente);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "actualizarEstatusAlumno")
    public boolean actualizarEstatusAlumno(@WebParam(name = "noControl") String noControl) {
        //TODO write your implementation code here:
        return alumnoDAO.actualizarEstatus(noControl);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "consultarAlumno")
    public Alumno consultarAlumno(@WebParam(name = "noControl") String noControl) {
        //TODO write your implementation code here:
        return alumnoDAO.consultarAlumno(noControl);
    }  
       
    /**
     * Web service operation
     */
    @WebMethod(operationName = "consultarAlumnos")
    public ArrayList<Alumno> consultarAlumnos() {
        //TODO write your implementation code here:
        return alumnoDAO.consultaGeneral();
    }    
}
