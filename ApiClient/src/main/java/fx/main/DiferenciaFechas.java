/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.main;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author oscar
 */
public class DiferenciaFechas {

  public static void main(String[] args) throws InterruptedException {
    Date date = new Date();
    Thread.sleep(2000);
    Date date2 =  new Date();
    LocalDateTime toDateTime = 
            date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    LocalDateTime fromDateTime = 
            date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    Duration d = Duration.between(fromDateTime, toDateTime);
    System.out.println(d.getSeconds());
    d.getSeconds();
    
  }

}
