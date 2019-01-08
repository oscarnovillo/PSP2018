/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;



import java.io.IOException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import model.Alumno;



/**
 *
 * @author oscar
 */
public class MainJersey {
    private static final String REST_URI 
      = "http://localhost:8080/baseDatos/rest/cutre";
  
   
 
    public static List<Alumno> getJsonEmployee(int id) throws IOException {
//         ClientConfig config = new ClientConfig();
//    config.register(JacksonJsonProvider.class);
//        
//        
        Client client = ClientBuilder.newClient();
        WebTarget webTarget 
  = client.target(REST_URI);
        
        return   webTarget.queryParam("alummno",new Alumno())
          .request(MediaType.APPLICATION_JSON).header("API_KEY", "KK")
          .get(new GenericType<List<Alumno>>(){});
//        ObjectMapper map = new ObjectMapper();
//        return map.readValue(s, Alumno.class);
    }
    
    
    
    public static void main(String[] args) throws IOException {
        System.out.println(getJsonEmployee(0).get(0).getNombre());
    }
}
