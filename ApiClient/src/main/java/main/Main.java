/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.util.List;
import model.Alumno;
import quevedo.apiclient.GoogleHttpConsumerFootball;
import quevedo.apiclient.GoogleHttpConsumingApi;

/**
 *
 * @author oscar
 */
public class Main {

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
            = new GenericUrl("http://localhost:8084/ServidorApiItemShop/rest/cutre");

    Alumno alumno = new Alumno();
    ObjectMapper m = new ObjectMapper();
    url.set("alumno", m.writeValueAsString(alumno));
    
    HttpRequest requestGoogle
            = requestFactory.buildGetRequest(url);
    requestGoogle.getHeaders().set("API_KEY", "KhhhK");
    HttpResponse response = null;
    try {
       response = requestGoogle.execute();
      Alumno a2 = response.parseAs(Alumno.class);
      System.out.println(a2.getNombre());
    } catch (HttpResponseException e) {
      System.out.println("Error codigo"+e.getStatusCode()+e.getContent());
    }

    

  }

}
