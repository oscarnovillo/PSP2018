/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Customer;
import model.Purchase;
import model.Review;
import services.CustomersServices;
import services.PurchasesServices;
import services.ReviewsServices;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLAddReviewController implements Initializable {

    @FXML
    private ListView clientBox;
    @FXML
    private ListView purchaseBox;
    @FXML
    private ComboBox ratingBox;
    @FXML
    private TextField titleBox;
    @FXML
    private TextArea textBox;
    @FXML
    private ListView reviewList;

    public void loadCustomers() {
        CustomersServices cs = new CustomersServices();
        List<Customer> customers = cs.getAllCustomers();
        if(customers != null){
            clientBox.getItems().setAll(customers);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not load the customers from the database");
            alert.showAndWait();
        }

    }

    public void loadRating() {
        for (int i = 0; i < 5; i++) {
            ratingBox.getItems().add(i, (Integer) i + 1);
        }

    }

    public void loadPurchases() {
        PurchasesServices ps = new PurchasesServices();
        if(ps.getAllPurchases() != null){
            int customerId = ((Customer) clientBox.getSelectionModel().getSelectedItem()).getIdCustomer();
            purchaseBox.getItems().setAll(ps.getPurchasesByClientId(customerId));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not load the information from the database");
            alert.showAndWait();
        }
        
    }

    public void addReview() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ReviewsServices rs = new ReviewsServices();
        
            if (clientBox.getSelectionModel().getSelectedItem() != null
                    && purchaseBox.getSelectionModel().getSelectedItem() != null) {

                if (ratingBox.getValue() != null) {

                    Review review = rs.createReview(ratingBox.getValue().toString(), titleBox.getText(),
                            textBox.getText(),
                            ((Customer) (clientBox.getSelectionModel().getSelectedItem())).getIdCustomer(),
                            ((Purchase) purchaseBox.getSelectionModel().getSelectedItem()).getItem(),
                            ((Purchase) purchaseBox.getSelectionModel().getSelectedItem()).getIdPurchase());

                    rs.addReview((Customer) clientBox.getSelectionModel().getSelectedItem(), review);
                    purchaseBox.getItems().clear();
                    titleBox.clear();
                    textBox.clear();

                    alert.setTitle("Review added!");
                    alert.setHeaderText(null);
                    alert.setContentText("The review was saved successfully!");
                    alert.showAndWait();

                } else {
                    alert.setTitle("Empty Fields");
                    alert.setHeaderText("Could not add the new review:");
                    alert.setContentText("Please, select a rating for the item reviewed.");
                    alert.showAndWait();
                }
            } else {

                alert.setTitle("Empty Fields");
                alert.setHeaderText("Could not add the new review:");
                alert.setContentText("Please, select a customer and a purchase.");
                alert.showAndWait();
            } 

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCustomers();
        loadRating();
    }

}
