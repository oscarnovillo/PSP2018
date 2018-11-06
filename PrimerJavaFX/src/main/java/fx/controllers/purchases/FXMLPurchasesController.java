/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;


import dao.ItemsDao;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import services.CustomersServices;
import services.PurchasesServices;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLPurchasesController implements Initializable {


    @FXML
    private ComboBox customerBox;
    @FXML
    private ComboBox itemBox;
    @FXML
    private ListView purchaseList;
    @FXML
    private DatePicker dateBox;

    
    public void load()
    {
        loadPurchasesList();
        loadItemsList();
        loadCustomersList();
    }
    
    public void loadPurchasesList() {
        PurchasesServices ps = new PurchasesServices();
        purchaseList.getItems().setAll(ps.getAllPurchases());

    }

    public void loadItemsList() {

        ItemsDao idao = new ItemsDao();
        itemBox.getItems().setAll(idao.getItems());

    }

    public void loadCustomersList() {
        CustomersServices cs = new CustomersServices();
        List<String> customersShort = cs.getShortCustomers();
        if (customersShort != null) {
            customerBox.getItems().setAll(customersShort);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not load the customers from the database");
            alert.showAndWait();
        }

    }

    public void addPurchase() {
        Alert alert = new Alert(AlertType.INFORMATION);
        PurchasesServices ps = new PurchasesServices();

        if (itemBox.getValue() != null
                && dateBox.getValue() != null) {
            
                ps.addPurchase(customerBox.getValue().toString(),
                        itemBox.getValue().toString(), dateBox.getValue().toString());
                loadPurchasesList();

        } else {

            alert.setTitle("Empty Fields");
            alert.setHeaderText("Could not add the new purchase:");
            alert.setContentText("One ore more fields are empty. Please fill all the fields before adding a purchase.");
            alert.showAndWait();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        loadPurchasesList();
//        loadItemsList();
//        loadCustomersList();
    }
}
