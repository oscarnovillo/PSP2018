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
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;
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
                = new GenericUrl("http://localhost:8080/ServidorApiItemShop/rest/cutre");

        Alumno alumno = new Alumno();
        ObjectMapper m = new ObjectMapper();
         m.registerModule(new JavaTimeModule());
         
        url.set("alumno", m.writeValueAsString(alumno));

        HttpRequest requestGoogle
                = requestFactory.buildGetRequest(url);
        requestGoogle.getHeaders().set("API_KEY", "KK");
        HttpResponse response = null;
        try {
            response = requestGoogle.execute();
            String s = response.parseAsString();
            ObjectMapper obj = new ObjectMapper();
            obj.registerModule(new JavaTimeModule());
            Alumno a2 = obj.readValue(s, new TypeReference<Alumno>() {
            });

            System.out.println(a2.getFecha_nacimiento());
//            response = requestGoogle.execute();
//            a2 = response.parseAs(Alumno.class);
//            System.out.println(a2.getFecha_nacimiento());

            requestGoogle
                    = requestFactory.buildPutRequest(url, ByteArrayContent.fromString(null, m.writeValueAsString(alumno)));
            requestGoogle.getHeaders().set("API_KEY", "KK");
            response = requestGoogle.execute();
             s = response.parseAsString();
            a2 = obj.readValue(s, new TypeReference<Alumno>() {
            });
            System.out.println(a2.getNombre());

            GenericData data = new GenericData();
            data.put("mensaje", "hola");
            
            requestGoogle
                    = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
            
            
            
            
        } catch (HttpResponseException e) {
            System.out.println("Error codigo" + e.getStatusCode() + e.getContent());
        }

    }

}
