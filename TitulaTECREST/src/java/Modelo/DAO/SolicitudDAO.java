/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.DTO.Administrativo;
import Modelo.DTO.Alumno;
import Modelo.DTO.Carrera;
import Modelo.DTO.Opcion;
import Modelo.DTO.Salida;
import Modelo.DTO.Solicitud;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author fcarr
 */
public class SolicitudDAO {
    public  Salida agregar(Solicitud solicitud)
    {  String sql = "{call sp_registrar_solicitud(?,?,?,?,?)}";
       Salida salida = new Salida();
        try {
            CallableStatement cs= ConexionDB.getInstance().getConnection().prepareCall(sql);
            cs.setString(1, solicitud.getTituloProyecto());
            cs.setInt(2, solicitud.getOpcion().getIdOpcion());
            cs.setInt(3, solicitud.getAlumno().getIdAlumno());
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.execute();
            salida.setEstatus(cs.getString(4));
            salida.setEstatus(cs.getString(5));
            cs.close();
            ConexionDB.getInstance().cerrar();
            
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar sp_regustrar_solicitud"+ex.getMessage());
        }
    
        return salida;
    }
     public Salida modificar(Solicitud solicitud)
    {
        return null;
    }
      public Salida eliminar(int idSolicitud)
    {
        return null; 
    }
       public Object consultaGeneral( )
    {
        String sql = "select idSolicitud,idAlumno,noControl,Alumno,tituloProyecto,"
                + "fechaRegistro,fechaAtencion,estatus,idOpcion,Opcion,idAdministrativo,"
                + "Coordinador,idCarrera,Carrera from vSolicitudes";
        Salida s = new Salida();
        ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
        
        try {
            Statement query = ConexionDB.getInstance().getConnection().createStatement();
            ResultSet rs= query.executeQuery(sql);
            while(rs.next())
            {
                Solicitud solicitud = new Solicitud();
                solicitud.setIdSolicitud(rs.getInt("idSolicitud"));
                Alumno a = new Alumno();
                a.setIdAlumno(rs.getInt("idAlumno"));
                a.setNoControl(rs.getString("noControl"));
                a.setNombre(rs.getString("Alumno"));
                Carrera c = new Carrera();
                c.setIdCarrera(rs.getInt("idCarrera"));
                c.setNombre(rs.getString("Carrera"));
                a.setCarrera(c);
                solicitud.setAlumno(a);
                solicitud.setTituloProyecto(rs.getString("tituloProyecto"));
                solicitud.setFechaRegistro(rs.getString("fechaRegistro"));
                solicitud.setFechaAtencion(rs.getString("fechaAtencion"));
                solicitud.setEstatus(rs.getString("estatus"));
                Opcion o = new Opcion();
                o.setIdOpcion(rs.getInt("idOpcion"));
                o.setNombre(rs.getString("Opcion"));
                solicitud.setOpcion(o);
                Administrativo ad = new Administrativo();
                ad.setIdAdministrativo(rs.getInt("idAdministrativo"));
                ad.setNombre(rs.getString("Coordinador"));
                solicitud.setAdministrativo(ad);
                solicitud.setTipoUsuario("");
                solicitudes.add(solicitud);
            }
            rs.close();
            query.close();
            ConexionDB.getInstance().cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar "+sql+", "+ex.getMessage());
            s.setEstatus("Error:");
            s.setMensaje("Error al ejecutar "+sql);
            return s;
        }
        if(solicitudes.size()>0)
             return solicitudes;
        else
        {
            s.setEstatus("OK");
            s.setMensaje("No hay solicitudes Registradas... ");
            return s;
        }
    }
       
       
        public Object consultaIndividual(int idSolicitud)
    {
           String sql = "select idSolicitud,idAlumno,noControl,Alumno,tituloProyecto,"
                + "fechaRegistro,fechaAtencion,estatus,idOpcion,Opcion,idAdministrativo,"
                + "Coordinador,idCarrera,Carrera from vSolicitudes where idSolicitud = ?";
        Salida s = new Salida();
        Solicitud solicitud = new Solicitud();
        
        try {
            PreparedStatement ps = ConexionDB.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1,idSolicitud);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
               
                solicitud.setIdSolicitud(rs.getInt("idSolicitud"));
                Alumno a = new Alumno();
                a.setIdAlumno(rs.getInt("idAlumno"));
                a.setNoControl(rs.getString("noControl"));
                a.setNombre(rs.getString("Alumno"));
                Carrera c = new Carrera();
                c.setIdCarrera(rs.getInt("idCarrera"));
                c.setNombre(rs.getString("Carrera"));
                a.setCarrera(c);
                solicitud.setAlumno(a);
                solicitud.setTituloProyecto(rs.getString("tituloProyecto"));
                solicitud.setFechaRegistro(rs.getString("fechaRegistro"));
                solicitud.setFechaAtencion(rs.getString("fechaAtencion"));
                solicitud.setEstatus(rs.getString("estatus"));
                Opcion o = new Opcion();
                o.setIdOpcion(rs.getInt("idOpcion"));
                o.setNombre(rs.getString("Opcion"));
                solicitud.setOpcion(o);
                Administrativo ad = new Administrativo();
                ad.setIdAdministrativo(rs.getInt("idAdministrativo"));
                ad.setNombre(rs.getString("Coordinador"));
                solicitud.setAdministrativo(ad);
                solicitud.setTipoUsuario("");
                
            }
            rs.close();
             ps.close();
            ConexionDB.getInstance().cerrar();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar "+sql+", "+ex.getMessage());
            s.setEstatus("Error:");
            s.setMensaje("Error al ejecutar "+sql);
            return s;
        }
        if(solicitud.getIdSolicitud()!=0)
             return solicitud;
        else
        {
            s.setEstatus("OK");
            s.setMensaje("No hay solicitudes con el folio: "+idSolicitud);
            return s;
        }
    }
}
