/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.customers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import services.CustomersServices;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLfindCustomerController implements Initializable {

    @FXML
    private TextField dniBox;
    @FXML
    private ListView customerList;
    
     public void searchById() {
        customerList.getItems().clear();
        CustomersServices cs = new CustomersServices();
        List<Customer> customers = cs.getAllCustomers();

        if(customers != null){
            if (dniBox.getText().equalsIgnoreCase("")) {
                customerList.getItems().setAll(customers);
            } else {
                customerList.getItems().setAll(cs.searchById(Integer.parseInt(dniBox.getText())));
            }
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not load the customers from the database");
            alert.showAndWait();
        }
        

    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
