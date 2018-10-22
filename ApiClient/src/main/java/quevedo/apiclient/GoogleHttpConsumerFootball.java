/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quevedo.apiclient;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.GenericData;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.lang.reflect.Type;
import java.util.List;



/**
 *
 * @author oscar
 */

public class GoogleHttpConsumerFootball  {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest()
      throws  IOException {
        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        JsonFactory JSON_FACTORY = new JacksonFactory();
        HttpRequestFactory requestFactory
          = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
              @Override
              public void initialize(HttpRequest request) {
                  request.setParser(new JsonObjectParser(JSON_FACTORY));
                  
              }
          });

        GenericUrl url = new GenericUrl("http://api.football-data.org/v2/competitions");
        url.set("plan", "TIER_ONE");
        //url = new GenericUrl("http://api.football-data.org/v1/teams/78");

        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        requestGoogle.getHeaders().set("X-Auth-Token", "2deee83e549c4a6e9709871d0fd58a0a");
        

        //response.getWriter().print(requestGoogle.execute().parseAsString());
        Type type = new TypeToken<GenericJson>() {}.getType();
        
       System.out.println(requestGoogle.execute().parseAsString());
        //response.getWriter().print("<br>");
       GenericJson json = (GenericJson) requestGoogle.execute().parseAs(type);

        System.out.println(json.toPrettyString());

        //response.getWriter().print("</body></html>");
    }

}
