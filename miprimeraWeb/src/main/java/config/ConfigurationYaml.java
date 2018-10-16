/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

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

  public static ConfigurationYaml getInstance(InputStream file) {
    if (config == null) {
      Yaml yaml = new Yaml();
      config = (ConfigurationYaml) yaml.loadAs(
              file,
              ConfigurationYaml.class);

    }
    return config;
  }

  private String user;
  private String pass;

  private List<String> casas;
  private Map<String, String> recetas;

  public List<String> getCasas() {
    return casas;
  }

  public void setCasas(List<String> casas) {
    this.casas = casas;
  }

  public Map<String, String> getRecetas() {
    return recetas;
  }

  public void setRecetas(Map<String, String> recetas) {
    this.recetas = recetas;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

}
