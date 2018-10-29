/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quevedo.apiclient;

import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Arrives;

/**
 *
 * @author oscar
 */

public class GoogleHttpConsumingApi  {

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
      throws IOException {

        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        JsonFactory JSON_FACTORY = new JacksonFactory();
        HttpRequestFactory requestFactory
          = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
              @Override
              public void initialize(HttpRequest request) {
                  request.setParser(new JsonObjectParser(JSON_FACTORY));
              }
          });

        GenericUrl url = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/geo/GetArriveStop.php");

        GenericData data = new GenericData();
        data.put("idClient", "WEB.SERV.oscar.novillo@iesquevedo.es");
        data.put("passKey", "4C7A2AC7-2AC4-4AAE-9E63-E27EEA72969E");
        data.put("idStop", "3727");
        

        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
        //requestGoogle.getHeaders().set("X-Auth-Token", "2deee83e549c4a6e9709871d0fd58a0a");
        url.set("idClient", "WEB.SERV.oscar.novillo@iesquevedo.es");
        url.set("passKey", "4C7A2AC7-2AC4-4AAE-9E63-E27EEA72969E");
        url.set("idStop", "3727");

        //url = new GenericUrl("http://api.football-data.org/v1/teams/745/players");
        Arrives arr = requestGoogle.execute().parseAs(Arrives.class);
        GenericJson json = requestGoogle.execute().parseAs(GenericJson.class);
        System.out.println(arr.getArrives().size());

        ArrayList arrives = (ArrayList) json.get("arrives");
        //response.getWriter().print("<html><body>");
        for (int i = 0; i < arrives.size(); i++) {
            ArrayMap arrive = (ArrayMap)arrives.get(i);
            System.out.print(arrive.get("busTimeLeft")+" ");
            System.out.print(arrive.get("lineId")+" ");
            System.out.print(arrive.get("busDistance")+" ");
            System.out.println();
        }
        
        data = new GenericData();
        data.put("idClient", "WEB.SERV.oscar.novillo@iesquevedo.es");
        data.put("passKey", "4C7A2AC7-2AC4-4AAE-9E63-E27EEA72969E");
        data.put("line", "76");
        data.put("direction", "PLAZA BEATA");
        url = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/geo/GetStopsLine.php");
        requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
        json = requestGoogle.execute().parseAs(GenericJson.class);
        
      ArrayList stops = (ArrayList) json.get("stop");
      //response.getWriter().print(json.get("destination"));
      for (int i = 0; i < stops.size(); i++) {
            ArrayMap stop = (ArrayMap)stops.get(i);
//            response.getWriter().print(stop.get("stopId")+" ");
//            response.getWriter().print(stop.get("name")+" ");
//           
//            response.getWriter().print("<br>");
        }
      
      
      
  
        
        
        
        //response.getWriter().print("</body></html>");
    }

}
