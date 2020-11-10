/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Modelo.DAO.SolicitudDAO;
import Modelo.DTO.Salida;
import Modelo.DTO.Solicitud;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author fcarr
 */
@Path("/Solicitudes")
public class SolicitudREST {

    @Context
    private UriInfo context;
    Gson gson;
    SolicitudDAO sdao;
    

    /**
     * Creates a new instance of SolicitudREST
     */
    public SolicitudREST() {
        gson = new Gson();
        sdao= new SolicitudDAO();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String registrar(String json)
    {
        Solicitud solicitud=gson.fromJson(json, Solicitud.class);
        Salida salida=sdao.agregar(solicitud);
        
       return gson.toJson(salida);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarSolicitudes()
     {  
         /*Salida s = new Salida();
        s.setEstatus("Ok");
        s.setMensaje("Hola...");  */
         Object objeto =  sdao.consultaGeneral();
         return gson.toJson(objeto);
     }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarSolicitud(@PathParam("id") int idSolicitud)
    {
        Object objeto = sdao.consultaIndividual(idSolicitud);
        return gson.toJson(objeto);
    }
    
  
}
