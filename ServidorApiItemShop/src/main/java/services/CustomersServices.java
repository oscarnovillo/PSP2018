/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CustomersDao;
import dao.PurchasesDao;
import java.util.ArrayList;
import model.Customer;
import model.Purchase;

/**
 *
 * @author Laura
 */
public class CustomersServices {

    public ArrayList<Customer> getAllCustomers() {
        CustomersDao dao = new CustomersDao();

        return dao.loadCustomers();
    }

    public ArrayList<String> getShortCustomers() {

        ArrayList<Customer> custo = getAllCustomers();
        ArrayList<String> custoVisual = null;
        if (custo != null) {
            custoVisual = new ArrayList();

            for (Customer c : custo) {
                custoVisual.add(c.toStringShort());
            }
        }
        return custoVisual;
    }

    public ArrayList<String> searchById(int id) {
        CustomersDao dao = new CustomersDao();
        PurchasesDao pdao = new PurchasesDao();
        ArrayList<String> customersId = new ArrayList();
        StringBuilder pcc = new StringBuilder();
        ArrayList<Customer> customers = dao.loadCustomers();
        ArrayList<Purchase> purchases = pdao.loadPurchasesList();

        //Loading Client info:
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getIdCustomer() == id) {
                customersId.add(customers.get(i).toStringReviews());
            }
        }
        //Loading client purchases
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getIdCustomer() == id) {
                pcc.append(purchases.get(i).toStringForClientInfo());
            }
        }
        //If the customer exists, add title and its purchases even if they are empty.
        if (!customersId.isEmpty()) {
            customersId.add("======      Purchases done by this client:     ======");
            customersId.add(pcc.toString());
        } else {
            customersId.add("The customer doesnt exist in our database.");
        }

        return customersId;
    }

    public void deleteCustomer(Customer customer) {
        CustomersDao dao = new CustomersDao();
        ArrayList<Customer> customers = dao.loadCustomers();
        
        customers.remove(customer);

        dao.saveCustomers(customers);
    }

    public Customer addCustomer(String customerId, String name, String phone, String address) {
        CustomersDao dao = new CustomersDao();
        ArrayList<Customer> customers = dao.loadCustomers();
        String customer = customerId.split(" - ")[0];
        Customer newCustomer = new Customer(Integer.parseInt(customer), name, phone, address);
        customers.add(newCustomer);
        dao.saveCustomers(customers);

        return newCustomer;
    }

    public boolean validateID(String text) {
        return text.matches("[0-9]*");
    }

    public boolean checkCustomerId(String id) {
        CustomersDao dao = new CustomersDao();
        ArrayList<Customer> customers = dao.loadCustomers();
        boolean repeatedID = false;
        for (int i = 0; i < customers.size(); i++) {
            if (Integer.parseInt(id) == customers.get(i).getIdCustomer()) {
                repeatedID = true;
            }
        }
        return repeatedID;
    }
}
