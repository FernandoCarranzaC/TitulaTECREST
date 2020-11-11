/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Modelo.DAO.OpcionesDAO;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author fcarr
 */
@Path("/Opciones")
public class OpcionesREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OpcionesREST
     */
    public OpcionesREST() {
    }

    /**
     * Retrieves representation of an instance of Servicios.OpcionesREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarOpciones() {
        //TODO return proper representation object
       OpcionesDAO odao=new OpcionesDAO();
       Gson gson = new Gson();
      return gson.toJson(odao.consultaGeneral());
    }

    /**
     * PUT method for updating or creating an instance of OpcionesREST
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
