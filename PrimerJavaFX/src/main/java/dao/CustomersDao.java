/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import configuration.ConfigProperties;
import fx.controllers.purchases.FXMLPurchasesController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Item;
import model.Review;
import model.converters.DateConverter;

/**
 *
 * @author Laura
 */
public class CustomersDao {

  public ArrayList<Customer> loadCustomers() {

    ArrayList<Customer> customers = new ArrayList();
    Path file = Paths.get(ConfigProperties.getInstance().getProperty("customers"));
    Charset charset = Charset.forName("UTF-8");
    BufferedReader reader = null;

    XStream xs = new XStream(new DomDriver());
    XStream.setupDefaultSecurity(xs);
    xs.allowTypesByWildcard(new String[]{"model.*"});

    xs.alias("customer", Customer.class);
    xs.alias("review", Review.class);

    xs.aliasField("id", Customer.class, "idCustomer");
    xs.aliasField("item", Review.class, "itemId");
    xs.addImplicitCollection(Customer.class, "reviews");

    xs.useAttributeFor(Customer.class, "idCustomer");
    xs.useAttributeFor(Review.class, "itemId");

    xs.registerConverter(new DateConverter());

    try {
      reader = Files.newBufferedReader(file, charset);
      customers = (ArrayList<Customer>) xs.fromXML(reader);

    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException ex) {
          Logger.getLogger(FXMLPurchasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return customers;
  }

  public void saveCustomers(ArrayList<Customer> customers) {
    Path file = Paths.get(ConfigProperties.getInstance().getProperty("customers"));
    Charset charset = Charset.forName("UTF-8");

    XStream xs = new XStream(new DomDriver());
    XStream.setupDefaultSecurity(xs);
    xs.allowTypesByWildcard(new String[]{"model.*"});

    xs.alias("customer", Customer.class);
    xs.alias("review", Review.class);

    xs.aliasField("id", Customer.class, "idCustomer");
    xs.aliasField("item", Review.class, "itemId");
    xs.addImplicitCollection(Customer.class, "reviews");

    xs.useAttributeFor(Customer.class, "idCustomer");
    xs.useAttributeFor(Review.class, "itemId");

    xs.registerConverter(new DateConverter());

    try {
      xs.toXML(customers, Files.newBufferedWriter(file, charset));
    } catch (IOException ex) {
      Logger.getLogger(CustomersDao.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  public Customer saveCustomers(Customer customers) {
    Customer cliente = null;
     ObjectMapper mapper = new ObjectMapper();
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
              "http://localhost:8084/ServidorApiItemShop/clientes");

      url.set("op","SAVE");
      url.set("cliente",mapper.writeValueAsString(cliente));
      
      
      HttpRequest requestGoogle = requestFactory.buildGetRequest(url);

      HttpResponse response = requestGoogle.execute();
      String json = response.parseAsString();

      if (response.getStatusCode() != 500) {
       

        cliente = mapper.readValue(json,
                new TypeReference<List<Item>>() {
        });
      } else {
        return null;
      }

    } catch (IOException ex) {
      Logger.getLogger(ItemsDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cliente;

  }

}
