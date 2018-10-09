/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author oscar
 */
public class ConfigListener implements ServletContextListener {

   @Override
    public void contextInitialized(ServletContextEvent sce) {
      ConfigurationYaml.getInstance(sce.getServletContext().getResourceAsStream("/WEB-INF/config.yml"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       //((HikariDataSource)DBConnection.getInstance().getDataSource()).close();
        
    }
}
