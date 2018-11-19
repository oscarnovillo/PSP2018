/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.reflect.TypeToken;
import dao.ItemsDao;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;

/**
 *
 * @author oscar
 */
public class SessionTest {
  
  public static void main(String[] args) {
    try {

      HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
      JsonFactory JSON_FACTORY = new JacksonFactory();
      HttpRequestFactory requestFactory
              = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) {
                  request.setParser(new JsonObjectParser(JSON_FACTORY));

                }
              });

      GenericUrl url = new GenericUrl(
              "http://localhost:8084/ServidorApiItemShop/session");

      HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
      HttpResponse response = requestGoogle.execute();
       System.out.println(requestGoogle.getResponseHeaders().getCookie());

      System.out.println(requestGoogle.execute().parseAsString());
       System.out.println(response.getHeaders().getCookie());
        System.out.println(requestGoogle.getResponseHeaders().getCookie());
      response = requestGoogle.execute();
       System.out.println(response.getHeaders().getCookie());
      System.out.println(response.parseAsString());
      requestFactory
              = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) {
                  request.setParser(new JsonObjectParser(JSON_FACTORY));

                }
              });
      requestGoogle = requestFactory.buildGetRequest(url);
      response = requestGoogle.execute();
      System.out.println(response.parseAsString());
       System.out.println(response.getHeaders().getCookie());
      response = requestGoogle.execute();
      System.out.println(response.parseAsString());
   
      System.out.println(response.getHeaders().getCookie());

    } catch (IOException ex) {
      Logger.getLogger(ItemsDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}
