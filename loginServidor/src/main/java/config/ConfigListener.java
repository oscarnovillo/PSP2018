/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import dao.DBConnectionPool;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author oscar
 */
@WebListener
public class ConfigListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    Configuration.getInstance(sce.getServletContext()
            .getResourceAsStream("/WEB-INF/config/configYaml.yml"));
    DBConnectionPool.getInstance().getDataSource();
    
   }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
 }
}
