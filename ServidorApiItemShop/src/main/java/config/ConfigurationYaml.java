/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;

import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author oscar
 */
public class ConfigurationYaml {

    private static ConfigurationYaml config;

    private ConfigurationYaml() {

    }

    public static ConfigurationYaml getInstance() {

        return config;
    }

    public static ConfigurationYaml getInstance(ServletContext contexto,InputStream file) {
        if (config == null) {
            Yaml yaml = new Yaml();
            config = (ConfigurationYaml) yaml.loadAs(
                    file,
                    ConfigurationYaml.class);
            config.setContexto(contexto);
        }
        return config;
    }

    private String fileItems;
    private String filePurchases;
    private String fileCustomers;
    
    private ServletContext contexto;

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

    public ServletContext getContexto() {
        return contexto;
    }

    public void setContexto(ServletContext contexto) {
        this.contexto = contexto;
    }
    

}
