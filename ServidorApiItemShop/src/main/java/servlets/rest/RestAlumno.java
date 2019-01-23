/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.rest;

import java.time.LocalDateTime;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import model.Alumno;

/**
 *
 * @author oscar
 */
@Path("/alumno")
public class RestAlumno {
    
    
    // This method is called if TEXT_PLAIN is request
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response sayPlainTextHello() {
      Alumno alumno = new Alumno();
        alumno.setNombre("Juan");
       
        
        alumno.setNombre("KIKO");
       
        alumno.setFecha_nacimiento(LocalDateTime.now());
       return Response.ok(alumno).status(Status.OK).build();
//    return  alumno;
  }
  
    // This method is called if TEXT_PLAIN is request
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response sayPut(Alumno alumno) {
      
      
       
        
        alumno.setNombre("KIKO");
       
        alumno.setFecha_nacimiento(LocalDateTime.now());
       return Response.ok(alumno).status(Status.OK).build();
//    return  alumno;
  }
  
  
  
}
