/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;

/**
 *
 * @author oscar
 */
@WebServlet(name = "AlumnoRecuperacion", urlPatterns = {"/rest/alumnoRecuperacion"})
public class AlumnoRecuperacion extends HttpServlet {

  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String alumnoJson = request.getParameter("alumno");
    ObjectMapper obj = new ObjectMapper();
    obj.registerModule(new JavaTimeModule());
    Alumno alumnoFiltro = obj.readValue(alumnoJson, new TypeReference<Alumno>() {
    });

    // ir a servicios a hacer la query
    
    Alumno alumno = new Alumno();
    alumno.setNombre("KIKO");
    alumno.setFecha_nacimiento(LocalDateTime.now());
    
    
    //obj.writeValue(response.getOutputStream(), alumno);
    
    response.getWriter().
            println(obj.writeValueAsString(alumno));
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
