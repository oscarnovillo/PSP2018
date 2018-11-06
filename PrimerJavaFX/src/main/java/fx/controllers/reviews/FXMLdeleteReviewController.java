/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import model.Customer;
import model.Review;
import services.CustomersServices;
import services.ReviewsServices;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLdeleteReviewController implements Initializable {

    @FXML
    private ListView customerBox;
    @FXML
    private ListView reviewBox;

    public void loadReviewsList() {
        CustomersServices cs = new CustomersServices();
        reviewBox.getItems().clear();
        if(!cs.getAllCustomers().isEmpty()){
            Customer cust = (Customer) customerBox.getSelectionModel().getSelectedItem();
            if (cust.getReviews() != null && !cust.getReviews().isEmpty()) {
                ArrayList<Review> reviews = cust.getReviews();
                reviewBox.getItems().setAll(reviews);
            }
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not load the customers from the database");
            alert.showAndWait();
        }
        

    }

    public void loadCustomersList() {
        CustomersServices cs = new CustomersServices();
        List<Customer> customers = cs.getAllCustomers();
        if(customers != null){
            customerBox.getItems().setAll(customers);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not load the customers from the database");
            alert.showAndWait();
        }

    }

    public void deleteReview() {
        ReviewsServices cs = new ReviewsServices();
        Alert alert = new Alert(AlertType.INFORMATION);

        if (reviewBox.getSelectionModel().getSelectedItem() != null
                && customerBox.getSelectionModel().getSelectedItem() != null) {
            Customer custo = (Customer) customerBox.getSelectionModel().getSelectedItem();
            Review rev = (Review) reviewBox.getSelectionModel().getSelectedItem();
            cs.deleteReview(custo, rev);

            alert.setTitle("Review deleted");
            alert.setHeaderText(null);
            alert.setContentText("The review was deleted successfully!");
            alert.showAndWait();
        } else {
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please, select a customer and a review from the list.");
            alert.showAndWait();
        }
        reviewBox.getItems().clear();
        loadCustomersList();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //loadCustomersList();
    }

}
