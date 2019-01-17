/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpCookie;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Alumno;
import org.glassfish.jersey.uri.UriComponent;

/**
 *
 * @author oscar
 */
public class MantenerSessionJersey {
    
     private static final String REST_URI
            = "http://localhost:8080/ServidorApiItemShop/session";
    
    public static void main(String[] args) throws JsonProcessingException, IOException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget
                = client.target(REST_URI);


        Response response = webTarget
                .request(MediaType.TEXT_PLAIN).get();
        String cookie = response.getHeaders().get("set-cookie").get(0).toString();
        
          if (Files.exists(Paths.get("cookie"))) {
            BufferedReader f = Files.newBufferedReader(Paths.get("cookie"));
            cookie = f.readLine();

        }      
        HttpCookie c = HttpCookie.parse(cookie).get(0);
        System.out.println(cookie);
        String out =  response.readEntity(String.class);
        System.out.println(out);

        
         response = webTarget
                .request(MediaType.TEXT_PLAIN).cookie(c.getName(),c.getValue()).get();
         out =  response.readEntity(String.class);
        System.out.println(out);

    }
 
}
