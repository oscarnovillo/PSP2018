/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author oscar
 */
@ApplicationPath("/jersey")
public class App extends ResourceConfig{
    public App() {
       packages("servlets.rest");
       ObjectMapperContextResolver obj = new ObjectMapperContextResolver();
       register(obj);  
       RestrictedOperationsRequestFilter rq = new RestrictedOperationsRequestFilter();
       register(rq);
    }
}
