/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.DTO.Alumno;
import modelo.DTO.Carrera;
import modelo.DTO.Estatus;
import modelo.DTO.Usuario;

/**
 *
 * @author roberto
 */
public class AlumnoDAO {

    public ArrayList<Alumno> consultaGeneral() {
        String sql = "select nocontrol,nombre_completo,sexo,promedio,creditos,"
                + "idcarrera,nombre_carrera,idestatus,estatus,telefono,email\n" +
                " from vAlumnos";
        ArrayList<Alumno> lista=new ArrayList<Alumno>();
        try{
            Statement s=Conexion.getInstance().getCn().createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                Alumno a=new Alumno();
                a.setNoControl(rs.getString("nocontrol"));
                Usuario u=new Usuario();
                u.setNombre(rs.getString("nombre_completo"));
                u.setSexo(rs.getString("sexo"));
                u.setTelefono(rs.getString("telefono"));
                u.setEmail(rs.getString("email"));
                a.setUsuario(u);
                a.setPromedio(rs.getFloat("promedio"));
                a.setCreditos(rs.getInt("creditos"));
                Carrera c=new Carrera();
                c.setIdCarrera(rs.getInt("idCarrera"));
                c.setNombre(rs.getString("nombre_carrera"));
                a.setCarrera(c);
                Estatus e=new Estatus();
                e.setIdEstatus(rs.getInt("idEstatus"));
                e.setNombre(rs.getString("estatus"));
                a.setEstatus(e);
               lista.add(a);
            }
            rs.close();
            s.close();
            Conexion.getInstance().cerrar();
        }
        catch(SQLException e){
            System.out.println("Error:"+e.getMessage());
        }
        return lista;
    }
    public boolean actualizarEstatus(String noControl){
        String sql="update alumnos set idEstatus=6 where noControl=?";
        boolean ban=false;
        try{
            PreparedStatement ps=Conexion.getInstance().getCn().prepareStatement(sql);
            ps.setString(1, noControl);
            ps.execute();
            ps.close();
            Conexion.getInstance().cerrar();
            ban=true;
        }
        catch(SQLException e){
            System.out.println("Error:"+e.getMessage());
        }
        return ban;
    }
    public Alumno consultarAlumno(String noControl){
        String sql ="select nocontrol,nombre_completo,sexo,promedio,creditos,"
                + "idcarrera,nombre_carrera,idestatus,estatus,telefono,email\n" +
                " from vAlumnos where nocontrol=?"; 
        Alumno a=new Alumno();
        try{
            PreparedStatement s=Conexion.getInstance().getCn().prepareStatement(sql);
            s.setString(1, noControl);
            ResultSet rs=s.executeQuery();
            if(rs.next()){
                a.setNoControl(rs.getString("nocontrol"));
                Usuario u=new Usuario();
                u.setNombre(rs.getString("nombre_completo"));
                u.setSexo(rs.getString("sexo"));
                u.setTelefono(rs.getString("telefono"));
                u.setEmail(rs.getString("email"));
                a.setUsuario(u);
                a.setPromedio(rs.getFloat("promedio"));
                a.setCreditos(rs.getInt("creditos"));
                Carrera c=new Carrera();
                c.setIdCarrera(rs.getInt("idCarrera"));
                c.setNombre(rs.getString("nombre_carrera"));
                a.setCarrera(c);
                Estatus e=new Estatus();
                e.setIdEstatus(rs.getInt("idEstatus"));
                e.setNombre(rs.getString("estatus"));
                a.setEstatus(e);
            }
            rs.close();
            s.close();
            Conexion.getInstance().cerrar();
        }
        catch(SQLException e){
            System.out.println("Error:"+e.getMessage());
        }
        return a;
    }
}
