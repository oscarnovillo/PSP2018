/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.time.LocalDateTime;
import java.util.List;
import model.Alumno;

/**
 *
 * @author oscar
 */
public class MainRecuperacion {

  public static void main(String[] args) throws Exception {


    put(10);
    put(12);
    put(13);
    put(18);
    get(13);
    getAll();
    delete(18);
    getAll();

  }

  public static void get(int id) throws JsonProcessingException, IOException {
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
    alumno.setId(id);
    ObjectMapper m = new ObjectMapper();
    m.registerModule(new JavaTimeModule());

    url.set("alumno", m.writeValueAsString(alumno));
    
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

   public static void getAll() throws JsonProcessingException, IOException {
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

   
    
    HttpRequest requestGoogle
            // la diferencia put, delete y get es este metodo
            = requestFactory.buildGetRequest(url);

    HttpResponse response = null;
    try {
      response = requestGoogle.execute();
      String s = response.parseAsString();
      ObjectMapper obj = new ObjectMapper();
      obj.registerModule(new JavaTimeModule());
      List<Alumno> a2 = obj.readValue(s, new TypeReference<List<Alumno>>() {
      });

      System.out.println(a2);

    } catch (HttpResponseException e) {
      System.out.println("Error codigo" + e.getStatusCode() + e.getContent());
    }
  }

  
  
  public static void put(int id) throws JsonProcessingException, IOException {
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
    alumno.setId(id);
    alumno.setNombre("julian");
    alumno.setFecha_nacimiento(LocalDateTime.now());
    
    ObjectMapper m = new ObjectMapper();
    m.registerModule(new JavaTimeModule());

    url.set("alumno", m.writeValueAsString(alumno));
    

    HttpRequest requestGoogle
            // la diferencia put, delete y get es este metodo
            = requestFactory.buildPutRequest(url,null);

    HttpResponse response = null;
    try {
      response = requestGoogle.execute();
      String s = response.parseAsString();
      System.out.println(s);

    } catch (HttpResponseException e) {
      System.out.println("Error codigo" + e.getStatusCode() + e.getContent());
    }
  }

  
   public static void delete(int id) throws JsonProcessingException, IOException {
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
    alumno.setId(id);
    
    ObjectMapper m = new ObjectMapper();
    m.registerModule(new JavaTimeModule());

    url.set("alumno", m.writeValueAsString(alumno));
    

    HttpRequest requestGoogle
            // la diferencia put, delete y get es este metodo
            = requestFactory.buildDeleteRequest(url);

    HttpResponse response = null;
    try {
      response = requestGoogle.execute();
      String s = response.parseAsString();
      System.out.println(s);

    } catch (HttpResponseException e) {
      System.out.println("Error codigo" + e.getStatusCode() + e.getContent());
    }
  }
  
   
   
   public static void post(int id) throws JsonProcessingException, IOException {
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
    alumno.setId(id);
    ObjectMapper m = new ObjectMapper();
    m.registerModule(new JavaTimeModule());

    GenericData data = new GenericData();
    data.set("alumno", m.writeValueAsString(alumno));

    HttpRequest requestGoogle
            // la diferencia put, delete y get es este metodo
            = requestFactory.buildPostRequest(url,new  UrlEncodedContent(data));

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
