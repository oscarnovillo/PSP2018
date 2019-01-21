/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.InputStream;
import java.util.List;
import java.util.Map;



/**
 *
 * @author oscar
 */
public class ConfigurationYaml {

    private static ConfigurationYaml config;

    private ConfigurationYaml() {

    }



    public static ConfigurationYaml getInstance() {
        if (config == null) {
            config = new ConfigurationYaml();
        }
        return config;
    }

    private String fileItems;
    private String filePurchases;
    private String fileCustomers;
    private String cookie;

  public String getCookie() {
    if (cookie == null)
      cookie = "";
    return cookie;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
  }
    
    


    public String getFileItems() {
        return fileItems;
    }

    public void setFileItems(String fileItems) {
        this.fileItems = fileItems;
    }

    public String getFilePurchases() {
        return filePurchases;
    }

    public void setFilePurchases(String filePurchases) {
        this.filePurchases = filePurchases;
    }

    public String getFileCustomers() {
        return fileCustomers;
    }

    public void setFileCustomers(String fileCustomers) {
        this.fileCustomers = fileCustomers;
    }

  
    

}
