/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.api.client.util.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author oscar
 */
public class Alumno {
    
    @Key
    private long id;
    @Key
    private String nombre;
    @Key
    private LocalDateTime fecha_nacimiento;
    private Boolean mayor_edad;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDateTime fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Boolean getMayor_edad() {
        return mayor_edad;
    }

    public void setMayor_edad(Boolean mayor_edad) {
        this.mayor_edad = mayor_edad;
    }

  
    public Alumno() {
    }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Alumno other = (Alumno) obj;
    if (this.id != other.id) {
      return false;
    }
    return true;
  }

   
    
}
