/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import services.CustomersServices;
import services.ItemsServices;
import services.ReviewsServices;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLfindReviewController implements Initializable {

    @FXML
    private ListView reviewList;
    @FXML
    private ComboBox itemBox;

    public void loadItems() {
        ItemsServices is = new ItemsServices();
        itemBox.getItems().setAll(is.getAllItems());
    }

    public void searchByItem() {
        
        reviewList.getItems().clear();
        ReviewsServices rs = new ReviewsServices();
        CustomersServices cs = new CustomersServices();
        if(cs.getAllCustomers() != null){
            String item = itemBox.getValue().toString();
            int id = Integer.parseInt(item.split(" ")[0]);
            reviewList.getItems().setAll(rs.searchByItem(id));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not load the reviews from the database");
            alert.showAndWait();
        }
        

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadItems();
    }

}
