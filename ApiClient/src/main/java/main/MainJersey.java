/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import model.Alumno;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import org.glassfish.jersey.uri.UriComponent;

/**
 *
 * @author oscar
 */
public class MainJersey {

  private static final String REST_URI
          = "http://localhost:8080/ServidorApiItemShop/rest/cutre";

  public static Alumno getJsonEmployee(int id) throws IOException {
//         ClientConfig config = new ClientConfig();
//    config.getClasses().add(ObjectMapperContextResolver.class);
//        
//        
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    JacksonJsonProvider provider = new JacksonJsonProvider(mapper);

    ObjectMapperContextResolver obj = new ObjectMapperContextResolver();
    Client client = ClientBuilder.newClient().register(obj).register(JacksonFeature.class);
    WebTarget webTarget
            = client.target(REST_URI);

    return webTarget.queryParam("alumno", UriComponent.encode(mapper.writeValueAsString(new Alumno()), UriComponent.Type.QUERY_PARAM_SPACE_ENCODED))
            .request(MediaType.APPLICATION_JSON).header("API_KEY", "KK")
            .get(new GenericType<Alumno>() {
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
    alumno.setFecha_nacimiento(LocalDateTime.now());
    ObjectMapperContextResolver obj = new ObjectMapperContextResolver();
    Client client = ClientBuilder.newClient().register(obj).register(JacksonFeature.class);
    WebTarget webTarget
            = client.target(REST_URI);
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    return webTarget.queryParam("alumno", UriComponent.encode(mapper.writeValueAsString(alumno), UriComponent.Type.QUERY_PARAM_SPACE_ENCODED))
            .request(MediaType.APPLICATION_JSON).header("API_KEY", "KK")
            .put(Entity.json(alumno), Alumno.class);
//        ObjectMapper map = new ObjectMapper();
//        return map.readValue(s, Alumno.class);
  }

  public static Alumno getJerseyAlumno() throws IOException {
//         ClientConfig config = new ClientConfig();
//    config.register(JacksonJsonProvider.class);
//        
//        
    Alumno alumno = new Alumno();
    alumno.setId(28);
    alumno.setMayor_edad(Boolean.TRUE);
    alumno.setFecha_nacimiento(LocalDateTime.now());
    ObjectMapperContextResolver obj = new ObjectMapperContextResolver();
    Client client = ClientBuilder.newClient().register(obj).register(JacksonFeature.class);
    WebTarget webTarget
            = client.target("http://localhost:8080/ServidorApiItemShop/jersey/alumno");
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    return webTarget.queryParam("alumno", UriComponent.encode(mapper.writeValueAsString(alumno), UriComponent.Type.QUERY_PARAM_SPACE_ENCODED))
            .request(MediaType.APPLICATION_JSON).header("API_KEY", "KK")
            .get(Alumno.class);
//        ObjectMapper map = new ObjectMapper();
//        return map.readValue(s, Alumno.class);
  }

  public static Alumno putAlumnoJersey() throws IOException {
//         ClientConfig config = new ClientConfig();
//    config.register(JacksonJsonProvider.class);
//        
//        
    Alumno alumno = new Alumno();
    alumno.setId(28);
    alumno.setNombre("jjj");
    alumno.setMayor_edad(Boolean.TRUE);
    alumno.setFecha_nacimiento(LocalDateTime.now());
    ObjectMapperContextResolver obj = new ObjectMapperContextResolver();
    Client client = ClientBuilder.newClient().register(obj).register(JacksonFeature.class);
    WebTarget webTarget
            = client.target("http://localhost:8080/ServidorApiItemShop/jersey/alumno");
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
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
    String r = webTarget.queryParam("apellido", "gonzalez")
            .request(MediaType.TEXT_HTML).header("API_KEY", "KK")
            .get(String.class);
    System.out.println(r);
//        ObjectMapper map = new ObjectMapper();
//        return map.readValue(s, Alumno.class);
  }

  public static void main(String[] args) throws IOException {
    System.out.println(getJsonEmployee(0).getFecha_nacimiento());
    System.out.println(putAlumno().getNombre());
    testHello();
    System.out.println(getJerseyAlumno().getFecha_nacimiento());
    System.out.println(putAlumnoJersey().getId());
  }
}
