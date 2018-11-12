/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Laura
 */
public class ItemsDao {

  public ArrayList<Item> getItems() {

    ArrayList<Item> itemArray = new ArrayList();
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
              "http://localhost:8084/ServidorApiItemShop/articulos");

      HttpRequest requestGoogle = requestFactory.buildGetRequest(url);

      Type type = new TypeToken<List<Item>>() {
      }.getType();
      itemArray = (ArrayList) requestGoogle.execute().parseAs(type);

      HttpResponse response = requestGoogle.execute();
      String json = response.parseAsString();

      if (response.getStatusCode() != 500) {
        ObjectMapper mapper = new ObjectMapper();

        itemArray = mapper.readValue(json,
                new TypeReference<List<Item>>() {
        });
      }
      else
      {
        return null;
      }

    } catch (IOException ex) {
      Logger.getLogger(ItemsDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return itemArray;
  }

}
