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

  public static ArrayList<Alumno> alumnos = new ArrayList<>();

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
    if (alumnoJson != null) {

      Alumno alumnoFiltro = obj.readValue(alumnoJson, new TypeReference<Alumno>() {
      });
      //obj.writeValue(response.getOutputStream(), alumno);
      if (alumnos.indexOf(alumnoFiltro) != -1) {
        response.getWriter().
                println(obj.writeValueAsString(
                        alumnos.get(
                                alumnos.indexOf(alumnoFiltro))));
      } else {
        response.setStatus(404);
        response.getWriter().
                println("alumno no existe");
      }
    } else {
      response.getWriter().
              println(obj.writeValueAsString(
                      alumnos));
    }

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

    if (null != request.getSession().getAttribute("tipo")) {
      String alumnoJson = request.getParameter("alumno");
      ObjectMapper obj = new ObjectMapper();
      obj.registerModule(new JavaTimeModule());
      Alumno alumnoFiltro = obj.readValue(alumnoJson, new TypeReference<Alumno>() {
      });
      alumnos.add(alumnoFiltro);

      response.getWriter().
              println("OK");
    } else {
      response.setStatus(403);
      response.getWriter().println("a donde ibas");

    }
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    if ("ADMIN".equals(request.getSession().getAttribute("tipo"))) {
      
      String alumnoJson = request.getParameter("alumno");
      ObjectMapper obj = new ObjectMapper();
      obj.registerModule(new JavaTimeModule());
      Alumno alumnoFiltro = obj.readValue(alumnoJson, new TypeReference<Alumno>() {
      });

      alumnos.add(alumnoFiltro);

      response.getWriter().
              println("OK");
    } else {
      response.setStatus(403);
      response.getWriter().println("a donde ibas");

    }
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    if ("ADMIN".equals(request.getSession().getAttribute("tipo"))) {
      String alumnoJson = request.getParameter("alumno");

      ObjectMapper obj = new ObjectMapper();
      obj.registerModule(new JavaTimeModule());
      Alumno alumnoFiltro = obj.readValue(alumnoJson, new TypeReference<Alumno>() {
      });

      boolean borrado = alumnos.remove(alumnoFiltro);

      if (borrado) {
        response.getWriter().
                println("OK");
      } else {
        response.setStatus(404);
        response.getWriter().
                println("alumno no existe");
      }
    } else {
      response.setStatus(403);
      response.getWriter().println("a donde ibas");

    }
  }

}
