/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.api.client.json.GenericJson;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.ErrorHttp;
import util.PasswordHash;

/**
 *
 * @author user
 */
@WebServlet(name = "RestCutre", urlPatterns = {"/rest/cutre"})
public class RestCutre extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        response.setContentType("application/json");
        
        String alumnoJson = request.getParameter("alumno");
        ObjectMapper obj = new ObjectMapper();
            obj.registerModule(new JavaTimeModule());
            Alumno a2 = obj.readValue(alumnoJson, new TypeReference<Alumno>() {
            });
        
        List<Alumno> alumnos = new ArrayList<>();
        Alumno alumno = new Alumno();
        alumno.setNombre("Juan");
        alumnos.add(alumno);
        alumno = new Alumno();
        alumno.setNombre("KIKO");
        alumnos.add(alumno);
        alumno.setFecha_nacimiento(LocalDateTime.now());
        request.setAttribute("json", alumno);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Alumno a = (Alumno) request.getAttribute("alumno");

        a.setNombre("DELETE");
        // if (alumno no se puede borrar)
        resp.setStatus(500);
        ErrorHttp error = new ErrorHttp("se rompio");

        request.setAttribute("json", error);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Alumno a = (Alumno) req.getAttribute("alumno");

        a.setNombre("PUT");
        Scanner scanner = new Scanner(req.getInputStream(), "UTF-8");
        String body = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Alumno a2 = mapper.readValue(body,
              new TypeReference<Alumno>() {
            });
        a2.setNombre("jjjjjjj");
        req.setAttribute("json", a2);
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

        //en caso de no existir filtro lo hacemos así.
        ObjectMapper mapper = new ObjectMapper();
        ((HttpServletRequest) request).getMethod();
        Alumno a = null;
        String alumno = request.getParameter("alumno");
        if (alumno != null) {
            a = mapper.readValue(alumno,
                    new TypeReference<Alumno>() {
            });
            //Alumno a = (Alumno) request.getAttribute("alumno");
            a.setNombre("conseguido");

            //request.setAttribute("json", a);
            mapper.writeValue(response.getOutputStream(), a);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
