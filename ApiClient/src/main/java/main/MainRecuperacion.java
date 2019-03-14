/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import model.Alumno;

/**
 *
 * @author oscar
 */
public class MainRecuperacion {

  public static void main(String[] args) throws IOException {
//    GoogleHttpConsumerFootball foot = new GoogleHttpConsumerFootball();
//    foot.processRequest();
//    GoogleHttpConsumingApi api = new GoogleHttpConsumingApi();
//    api.processRequest();

    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    JsonFactory JSON_FACTORY = new JacksonFactory();

    HttpRequestFactory requestFactory
            = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
              @Override
              public void initialize(HttpRequest request) {
                request.setParser(new JsonObjectParser(JSON_FACTORY));
              }
            });
    GenericUrl url
            = new GenericUrl(
                    "http://localhost:8084/ServidorApiItemShop/rest/alumnoRecuperacion");


    Alumno alumno = new Alumno();
    alumno.setId(10);
    ObjectMapper m = new ObjectMapper();
    m.registerModule(new JavaTimeModule());

    url.set("alumno", m.writeValueAsString(alumno));
    url.set("id", "10");

     HttpRequest requestGoogle
            // la diferencia put, delete y get es este metodo
            = requestFactory.buildGetRequest(url);
    
    HttpResponse response = null;
    try {
      response = requestGoogle.execute();
      String s = response.parseAsString();
      ObjectMapper obj = new ObjectMapper();
      obj.registerModule(new JavaTimeModule());
      Alumno a2 = obj.readValue(s, new TypeReference<Alumno>() {
      });

      System.out.println(a2);

    } catch (HttpResponseException e) {
      System.out.println("Error codigo" + e.getStatusCode() + e.getContent());
    }

  }

}
