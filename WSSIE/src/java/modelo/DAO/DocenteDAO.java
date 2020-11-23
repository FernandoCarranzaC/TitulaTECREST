/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.DTO.Docente;
import modelo.DTO.Usuario;

/**
 *
 * @author roberto
 */
public class DocenteDAO {

    public Docente consultaIndividual(int noDocente) {
        String sql = "select noDocente,idCarrera,d.idUsuario,escolaridad,especialidad,\n"
                + "cedula,d.estatus estatusDocente,nombre,sexo,telefono,email,clave,tipo,u.estatus \n"
                + "from Docentes d join usuarios u \n"
                + "on d.idUsuario=u.IDUSUARIO \n"
                + "where noDocente=? and d.estatus='A' and u.estatus='A'";
        Docente docente=new Docente();
        try{
            PreparedStatement ps=Conexion.getInstance().getCn().prepareStatement(sql);
            ps.setInt(1, noDocente);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                docente.setNoDocente(rs.getInt("noDocente"));
                docente.setIdCarrera(rs.getInt("idCarrera"));
                docente.setEscolaridad(rs.getString("escolaridad"));
                docente.setEspecialidad(rs.getString("especialidad"));
                docente.setCedula(rs.getString("cedula"));
                docente.setEstatus(rs.getString("estatusDocente"));
                Usuario u=new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString("nombre"));
                u.setSexo(rs.getString("sexo"));
                u.setTelefono(rs.getString("telefono"));
                u.setEmail(rs.getString("email"));
                u.setClave(rs.getString("clave"));
                u.setTipo(rs.getString("tipo"));
                u.setEstatus(rs.getString("estatus"));
                docente.setUsuario(u);
            }
            ps.close();
            rs.close();
            Conexion.getInstance().cerrar();
        }
        catch(SQLException e){
            System.out.println("Error:"+e.getMessage());
        }
        return docente;
    }
}
