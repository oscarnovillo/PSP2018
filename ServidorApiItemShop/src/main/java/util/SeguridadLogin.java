/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oscar
 */
public class SeguridadLogin {

  public boolean seguridad(HttpServletRequest request, HttpServletResponse response) {

    boolean pasa = false;

    
    if (request.getServletPath().contains("login"))
      pasa =true;
    else if (request.getMethod().equals("GET")) {
      pasa = true;
    } else if (request.getSession().getAttribute("tipo") != null) {
      pasa = false;
    }

    return pasa;
  }

}
