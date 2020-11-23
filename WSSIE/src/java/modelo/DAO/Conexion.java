/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author roberto
 */
public class Conexion {
    private static Conexion conexion;
    private Connection cn;
    private Conexion(){
        try{
            Context ctx=new InitialContext();
            DataSource ds=(DataSource)ctx.lookup("java:app/jdbc/SIE");
            cn=ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static Conexion getInstance(){
        if(conexion==null){
            conexion=new Conexion();
        }
        return conexion;
    }
    public Connection getCn(){
        return cn;
    }
    public void cerrar(){
        try{
            cn.close();
            conexion=null;
        }
        catch(SQLException e){
            System.out.println("Error:"+e.getMessage());
        }
    }
    
}
