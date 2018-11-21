/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CustomersDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Item;
import services.CustomersServices;

/**
 *
 * @author oscar
 */
@WebServlet(name = "Clientes", urlPatterns = {"/clientes"})
public class Clientes extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
     List<Customer> customers = new LinkedList<>();
        customers.add(new Customer(1,"n","p","a"));
        customers.add(new Customer(1,"n","p","a"));
        customers.add(new Customer(1,"n","p","a"));
        customers.add(new Customer(1,"n","p","a"));
        customers.add(new Customer(1,"n","p","a"));
        CustomersDao dao = new CustomersDao();
        dao.saveCustomers(customers);
    String op = request.getParameter("op");
    op = "";
    switch (op) {
      case "LIST":

        break;
      case "SAVE":
        
//        String clienteJson = request.getParameter("cliente");
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        Customer cliente = mapper.readValue(clienteJson,
//                new TypeReference<Customer>() {
//        });
//        CustomersServices cs = new CustomersServices();
//        if (cs.addCustomer(cliente) != null)
//        {
//          mapper.writeValue(
//                  response.getOutputStream()
//                  , cliente);
//        }
//        else
//        {
//          response.setStatus(500);
//          response.getWriter().print("ERROR");
//        }
        
        break;

    }
  }

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
    processRequest(request, response);
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
    processRequest(request, response);
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
