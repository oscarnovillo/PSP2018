/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import config.ConfigurationYaml;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.Configuration;
import model.Alumno;

/**
 *
 * @author oscar
 */
public class MantenerJwt {

  public static void main(String[] args) throws JsonProcessingException, IOException {

    GenericUrl url
            = new GenericUrl("http://localhost:8084/ServidorApiItemShop/jwt");

    String cookie = null;
//        if (Files.exists(Paths.get("cookie"))) {
//            BufferedReader f = Files.newBufferedReader(Paths.get("cookie"));
//            cookie = f.readLine();
//
//        }

    HttpRequest requestGoogle
            = getRequest(url);
    if (cookie != null) {
      requestGoogle.getHeaders().setCookie(cookie);
    }

    HttpResponse response = null;
    String jwt = "";
    try {
      response = executeResponse(requestGoogle);

      //response = requestGoogle.execute();
      if (((List<String>) response.getHeaders().get("set-cookie")) != null) {
        PrintWriter p = new PrintWriter("cookie");
        cookie = ((List<String>) response.getHeaders().get("set-cookie")).get(0);
        p.print(cookie);
        p.close();
        ConfigurationYaml.getInstance().setCookie(cookie);
      }

      jwt = ((List<String>) response.getHeaders().get("JWT"))
              .get(0);

      System.out.println(jwt);

      System.out.println(response.parseAsString());
    } catch (HttpResponseException e) {
      System.out.println("Error codigo" + e.getStatusCode() + e.getContent());
    }

    url
            = new GenericUrl("http://localhost:8084/ServidorApiItemShop/checkJWT");

    requestGoogle
            = getRequest(url);

    requestGoogle.getHeaders().set("JWT", jwt);
    try {

      response = requestGoogle.execute();
      System.out.println(response.parseAsString());
    } catch (HttpResponseException e) {
      System.out.println("Error codigo" + e.getStatusCode() + e.getContent());
    }

  }

  public static HttpRequest getRequest(GenericUrl url) {
    HttpRequest request = null;
    try {

      HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
      JsonFactory JSON_FACTORY = new JacksonFactory();
      HttpRequestFactory requestFactory
              = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) {
                  request.setParser(new JsonObjectParser(JSON_FACTORY));
                }
              });
      request
              = requestFactory.buildGetRequest(url);

      request.getHeaders().setCookie(
              ConfigurationYaml.getInstance().getCookie());

    } catch (IOException ex) {
      Logger.getLogger(MantenerJwt.class.getName()).log(Level.SEVERE, null, ex);
    }
    return request;
  }

  public static HttpResponse executeResponse(HttpRequest requestGoogle) {
    HttpResponse response = null;
    String cookie;
    try {
      response = requestGoogle.execute();
      if (((List<String>) response.getHeaders().get("set-cookie")) != null) {
        PrintWriter p = new PrintWriter("cookie");
        cookie = ((List<String>) response.getHeaders().get("set-cookie")).get(0);
        p.print(cookie);
        p.close();
        ConfigurationYaml.getInstance().setCookie(cookie);
      }

    } catch (IOException ex) {
      Logger.getLogger(MantenerJwt.class.getName()).log(Level.SEVERE, null, ex);
    }
    return response;
  }
}
