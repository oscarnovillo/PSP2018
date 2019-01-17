/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class Utils {

  private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public static String randomAlphaNumeric(int count) {
//        StringBuilder builder = new StringBuilder();
//        Random r = new SecureRandom();
//
//        while (count-- != 0) {
//            int character = (int) (r.nextInt(ALPHA_NUMERIC_STRING.length()));
//            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
//        }
//        return builder.toString();
    SecureRandom secure = new SecureRandom();
    byte[] random = new byte[count];
    secure.nextBytes(random);
    String cadena;
    cadena = Base64.getEncoder().encodeToString(random);

    return cadena;

  }

}
