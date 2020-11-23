/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteWSSIE;

import ClienteWS.Alumno;
import java.util.List;

/**
 *
 * @author fcarr
 */
public class ClienteWSSIE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Alumno> lista = consultarAlumnos();
        for(Alumno alumno:lista)
        {
            System.out.println(alumno.getUsuario().getNombre());
        }
    }

    private static java.util.List<ClienteWS.Alumno> consultarAlumnos() {
        ClienteWS.SIEService_Service service = new ClienteWS.SIEService_Service();
        ClienteWS.SIEService port = service.getSIEServicePort();
        return port.consultarAlumnos();
    }
    
}
