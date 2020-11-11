/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.DTO.Opcion;
import Modelo.DTO.Salida;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author fcarr
 */
public class OpcionesDAO {
    public Object consultaGeneral()
    {
        String sql = "select idOpcion,nombre from Opciones where estatus='A'";
        Salida s=new Salida();
        ArrayList<Opcion> opciones =new ArrayList<Opcion>();
        try {
                Statement st= ConexionDB.getInstance().getConnection().createStatement();
                ResultSet rs=st.executeQuery(sql);              
                while(rs.next())
                {
                    Opcion o = new Opcion();
                    o.setIdOpcion(rs.getInt("idOpcion"));
                    o.setNombre(rs.getString("nombre"));
                    opciones.add(o);                 
                }
                rs.close();
                st.close();
                ConexionDB.getInstance().cerrar();
                
        } catch (SQLException ex) {
               System.out.println("Error al ejecutar "+sql+", "+ex.getMessage());
            s.setEstatus("Error:");
            s.setMensaje("Error al ejecutar "+sql);       
        }
           if(opciones.size()>0)
               return opciones;
           else {
               s.setEstatus("OK");
               s.setMensaje("No hay opciones Registradas");
               return s;
           }
    }
}
