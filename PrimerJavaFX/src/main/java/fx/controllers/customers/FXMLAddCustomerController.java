/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.customers;

import java.net.URL;
import java.util.ArrayList;
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
public class FXMLAddCustomerController implements Initializable {

    @FXML
    private TextField idBox;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private TextField addressBox;
    @FXML
    private ListView customerList;

    public void loadCustomersList() {
        CustomersServices cs = new CustomersServices();
        if (cs.getAllCustomers() != null) {
            customerList.getItems().setAll(cs.getAllCustomers());
        }

    }

    public void addCustomer() {
        Alert alert = new Alert(AlertType.INFORMATION);
        CustomersServices cs = new CustomersServices();

        if (!idBox.getText().equals("")
                && !nameBox.getText().equals("")
                && !phoneBox.getText().equals("")) {

            if (cs.validateID(idBox.getText())) {
                if (!cs.getAllCustomers().isEmpty()) {
                    if (!cs.checkCustomerId(idBox.getText())) {

                        cs.addCustomer(idBox.getText(), nameBox.getText(),
                                phoneBox.getText(), addressBox.getText());
                        loadCustomersList();

                    } else {

                        alert.setTitle("ID Error");
                        alert.setHeaderText("Could not add the new customer:");
                        alert.setContentText("The customer ID already exists.");
                        alert.showAndWait();
                    }
                } else {
                    
                    cs.addCustomer(idBox.getText(), nameBox.getText(),
                            phoneBox.getText(), addressBox.getText());
                    loadCustomersList();
                }

            } else {

                alert.setTitle("ID Error");
                alert.setHeaderText("Could not add the new customer:");
                alert.setContentText("The customer ID must be a valid number.");
                alert.showAndWait();

            }

        } else {

            alert.setTitle("Empty Fields");
            alert.setHeaderText("Could not add the new customer:");
            alert.setContentText("Id, Name or Telephone fields are empty. Please fill the fields with valid ifnromation.");
            alert.showAndWait();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCustomersList();
    }

}
