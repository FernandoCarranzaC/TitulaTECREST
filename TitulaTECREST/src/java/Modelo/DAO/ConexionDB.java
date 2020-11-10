/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author fcarr
 */
public class ConexionDB { // Patron Singleton
    static ConexionDB instance;
    private Connection connection;  //coneccion para consultar la base de datos 
                                    // para ejecutar instrucciones de la base de datos, consultas, procdimientos
                                    // almacenados
    private ConexionDB() // como es privado solo se utiliza en el interior de la clase
    {  // Se creo la fuente de datos
        try {
            Context context = new InitialContext();
            DataSource ds= (DataSource) context.lookup("java:app/jdbc/TitulaTEC_SOA");
            connection = ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    // para utilizarlo creamos un método
    static public ConexionDB getInstance()
    {
        if(instance== null)
        {
            instance= new ConexionDB();
        }
        return instance;
    }
    // es para crear una sola conexión durante todo el ciclo de vida de ejecución del programa
    
    public Connection getConnection()
    {
        return connection;
    }
    
    public void cerrar()
    {
        try {
            connection.close();
            instance=null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    } // se cierra para que el administrador de aplicaciones siga con el control del objeto
    // y es objeto se regresa al pool de conexiones
}
