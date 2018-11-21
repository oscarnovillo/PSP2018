/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.api.client.util.Key;
import java.time.LocalDate;
import model.converters.LocalDateDeserializer;
import model.converters.LocalDateSerializer;

/**
 *
 * @author Laura
 */
public class Item {

  private int idItem;
  private String name;
  private String company;
  private double price;
  
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate fecha;

  public Item() {
  }

  public Item(int idItem, String name, String company, double price) {
    this.idItem = idItem;
    this.name = name;
    this.company = company;
    this.price = price;
    this.fecha = LocalDate.now();
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public int getIdItem() {
    return idItem;
  }

  public void setIdItem(int idItem) {
    this.idItem = idItem;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return idItem + " " + name + " " + price + "€" + fecha;
  }

  public String toStringTextFile() {
    return idItem + ";" + name + ";" + company + ";" + price;
  }

  public String toStringVisual() {
    return "ID: " + idItem + "  Name: " + name + "  Company: " + company + " Price: " + price;
  }

}
