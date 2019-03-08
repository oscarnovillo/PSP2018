/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navidad2019.spiderman;

import java.util.Scanner;

/**
 *
 * @author oscar
 */
public class Solucion {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int casos = sc.nextInt();
    for (int i = 0; i < casos; i++) {
      int posicionSpiderman = sc.nextInt();
      int primeraBomba = sc.nextInt();
      int segundaBomba = sc.nextInt();
      if (primeraBomba > segundaBomba) {
        int swap = primeraBomba;
        primeraBomba = segundaBomba;
        segundaBomba = swap;
      }
      int salida = 0;

      if (posicionSpiderman <= primeraBomba) {
        salida = primeraBomba - posicionSpiderman;
        salida += segundaBomba - primeraBomba;
        
      } else if ((posicionSpiderman > primeraBomba) && (posicionSpiderman < segundaBomba)) {
        int restaPrimera = posicionSpiderman - primeraBomba;
        int restaSegunda = segundaBomba - posicionSpiderman;
        if (restaPrimera > restaSegunda) {
          int swap = restaPrimera;
          restaPrimera = restaSegunda;
          restaSegunda = swap;
        }

        salida = restaPrimera * 2;
        salida += restaSegunda;
      } else {
        salida = posicionSpiderman - segundaBomba;
        salida += segundaBomba - primeraBomba;

      }
      System.out.println(salida);
    }
  }

}
