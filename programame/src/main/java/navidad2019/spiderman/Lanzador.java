/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navidad2019.spiderman;

import java.io.ByteArrayInputStream;

/**
 *
 * @author oscar
 */
public class Lanzador {
  
  public static void main(String[] args) {
    
    String entrada = "6\r\n200 150 200\r\n300 10 310\r\n800 10 20\r\n200 200 150\r\n300 310 10\r\n800 20 10\r\n";
    
   final ByteArrayInputStream input 
		= new ByteArrayInputStream(entrada.getBytes());
		System.setIn(input);
       
    Solucion.main(args);
   
     
  
    
  }
  
}
