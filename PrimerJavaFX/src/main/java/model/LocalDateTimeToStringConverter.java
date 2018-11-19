/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.databind.util.StdConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LocalDateTimeToStringConverter extends StdConverter<LocalDate, String> {


  @Override
  public String convert(LocalDate value) {
      DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
      return value.format(pattern);
  }
}