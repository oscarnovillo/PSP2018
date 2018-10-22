/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import quevedo.apiclient.GoogleHttpConsumerFootball;
import quevedo.apiclient.GoogleHttpConsumingApi;

/**
 *
 * @author oscar
 */
public class Main {
  
  public static void main(String[] args) throws IOException {
    GoogleHttpConsumerFootball foot = new GoogleHttpConsumerFootball();
    foot.processRequest();
//    GoogleHttpConsumingApi api = new GoogleHttpConsumingApi();
//    api.processRequest();
            
    
  }
  
}
