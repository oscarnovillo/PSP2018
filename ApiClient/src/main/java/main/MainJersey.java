/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import model.Alumno;
import org.glassfish.jersey.uri.UriComponent;

/**
 *
 * @author oscar
 */
public class MainJersey {

    private static final String REST_URI
            = "http://localhost:8080/ServidorApiItemShop/rest/cutre";

    public static List<Alumno> getJsonEmployee(int id) throws IOException {
//         ClientConfig config = new ClientConfig();
//    config.register(JacksonJsonProvider.class);
//        
//        
        Client client = ClientBuilder.newClient();
        WebTarget webTarget
                = client.target(REST_URI);

        ObjectMapper mapper = new ObjectMapper();

        return webTarget.queryParam("alumno", UriComponent.encode(mapper.writeValueAsString(new Alumno()), UriComponent.Type.QUERY_PARAM_SPACE_ENCODED))
                .request(MediaType.APPLICATION_JSON).header("API_KEY", "KK")
                .get(new GenericType<List<Alumno>>() {
                });
//        ObjectMapper map = new ObjectMapper();
//        return map.readValue(s, Alumno.class);
    }

    public static Alumno putAlumno() throws IOException {
//         ClientConfig config = new ClientConfig();
//    config.register(JacksonJsonProvider.class);
//        
//        
        Alumno alumno = new Alumno();
        alumno.setId(28);
        alumno.setMayor_edad(Boolean.TRUE);
        Client client = ClientBuilder.newClient();
        WebTarget webTarget
                = client.target(REST_URI);
        ObjectMapper mapper = new ObjectMapper();
        return webTarget.queryParam("alumno", UriComponent.encode(mapper.writeValueAsString(alumno), UriComponent.Type.QUERY_PARAM_SPACE_ENCODED))
                .request(MediaType.APPLICATION_JSON).header("API_KEY", "KK")
                .put(Entity.json(alumno), Alumno.class);
//        ObjectMapper map = new ObjectMapper();
//        return map.readValue(s, Alumno.class);
    }

    
     public static void testHello() throws IOException {
//         ClientConfig config = new ClientConfig();
//    config.register(JacksonJsonProvider.class);
//        
//        
        Client client = ClientBuilder.newClient();
        WebTarget webTarget
                = client.target("http://localhost:8080/ServidorApiItemShop/jersey/hello/juan");
        ObjectMapper mapper = new ObjectMapper();
         String r = webTarget.queryParam("apellido","gonzalez")
                .request(MediaType.TEXT_HTML)
                .get(String.class);
         System.out.println(r);
//        ObjectMapper map = new ObjectMapper();
//        return map.readValue(s, Alumno.class);
    }
    
    public static void main(String[] args) throws IOException {
        //System.out.println(getJsonEmployee(0).get(0).getNombre());
        //System.out.println(putAlumno().getNombre());
        testHello();
    }
}
