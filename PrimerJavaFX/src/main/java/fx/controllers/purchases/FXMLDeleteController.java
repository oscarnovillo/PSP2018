/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import services.PurchasesServices;
import model.Purchase;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLDeleteController implements Initializable {

    @FXML
    private ListView purchaseBox;


    public void loadPurchases() {
        PurchasesServices ps = new PurchasesServices();
        purchaseBox.getItems().setAll(ps.getAllPurchases());
        
    }
    
    public void deletePurchase(){
        PurchasesServices ps = new PurchasesServices();
        Alert alert = new Alert(AlertType.INFORMATION);
        if(purchaseBox.getSelectionModel().getSelectedItem() != null){
            
            ps.deletePurchase((Purchase) purchaseBox.getSelectionModel().getSelectedItem());
            purchaseBox.getItems().clear();
            loadPurchases();
            
            alert.setTitle("Purchase deleted");
            alert.setHeaderText(null);
            alert.setContentText("The purchase was deleted successfully!");
            alert.showAndWait();
        }else{
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please, select a purchase from the list.");
            alert.showAndWait();
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPurchases();
    }

}
