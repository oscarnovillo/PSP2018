/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author oscar
 */
import com.fasterxml.jackson.databind.util.StdConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDatetimeConverter extends StdConverter<String, LocalDate> {

  @Override
  public LocalDate convert(String value) {
       DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      return LocalDate.parse(value, pattern);
  }
}
