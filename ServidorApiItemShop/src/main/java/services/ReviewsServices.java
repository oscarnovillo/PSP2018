/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CustomersDao;
import java.util.ArrayList;
import model.Customer;
import model.Review;

/**
 *
 * @author Laura
 */
public class ReviewsServices {



    public ArrayList<Review> getAllReviews() {
        CustomersDao dao = new CustomersDao();
        ArrayList<Customer> cust = dao.loadCustomers();
        ArrayList<Review> rev = new ArrayList();
        if (cust != null) {
            for (int i = 0; i < cust.size(); i++) {
                if (cust.get(i).getReviews() != null) {
                    rev.addAll(cust.get(i).getReviews());
                }
            }
        }
        return rev;
    }

    public void deleteReview(Customer customer, Review review) {
        CustomersDao dao = new CustomersDao();
        ArrayList<Customer> customers = dao.loadCustomers();
        if (dao.loadCustomers() != null) {
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getIdCustomer() == customer.getIdCustomer()) {
                    ArrayList<Review> reviews = customers.get(i).getReviews();
                    for (int j = 0; j < reviews.size(); j++) {
                        if (reviews.get(j).getIdReview() == review.getIdReview()) {
                            reviews.remove(j);
                        }
                    }
                    customers.get(i).setReviews(reviews);

                }
            }
        }

        dao.saveCustomers(customers);
    }

    public ArrayList<String> searchByItem(int id) {
        CustomersDao dao = new CustomersDao();
        ArrayList<Customer> customers = dao.loadCustomers();
        ArrayList<Review> allReviews = new ArrayList();
        ArrayList<String> reviewsVisual = new ArrayList();

        if (dao.loadCustomers() != null) {
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getReviews() != null) {
                    allReviews.addAll(customers.get(i).getReviews());
                }
            }
            if (!allReviews.isEmpty()) {
                for (int i = 0; i < allReviews.size(); i++) {
                    if (allReviews.get(i).getItem() == id) {
                        reviewsVisual.add(allReviews.get(i).toStringVisual());
                    }
                }
            }
        }

        return reviewsVisual;
    }

    public Review createReview(String rating, String title,
            String description, int customerId, String itemId, int purchaseId) {
        
        int generatedId = generateNewId();
        
        return new Review(generatedId, Integer.parseInt(rating),
                title, description, customerId, Integer.parseInt(itemId), purchaseId);

    }

    public void addReview(Customer customer, Review review) {
        CustomersServices cs = new CustomersServices();
        ArrayList<Customer> allCustomers = cs.getAllCustomers();
        ArrayList<Review> newReview = new ArrayList();
  
        for (int i = 0; i < allCustomers.size(); i++) {
            if (customer.equals(allCustomers.get(i))) {
                if(allCustomers.get(i).getReviews() !=null){
                    allCustomers.get(i).addReview(review);
                }else{
                    newReview.add(review);
                    allCustomers.get(i).setReviews(newReview);
                }
                
            }
        }

        CustomersDao dao = new CustomersDao();
        dao.saveCustomers(allCustomers);
    }

    public boolean checkReviewId(String id) {
        boolean isRepeated = false;
        ArrayList<Review> rev = getAllReviews();
        for (int i = 0; i < rev.size(); i++) {
            if (rev.get(i).getIdReview() == Integer.parseInt(id)) {
                isRepeated = true;
            }
        }
        return isRepeated;
    }

    public boolean validateID(String text) {
        return text.matches("[0-9]*");
    }
    
    public int generateNewId(){
        CustomersDao dao = new CustomersDao();
        ArrayList<Customer> customers = dao.loadCustomers();
        ArrayList<Review> allReviews = new ArrayList();
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getReviews() != null){
                allReviews.addAll(customers.get(i).getReviews());
            }  
        }
        
        int newId = 1;
        if(!allReviews.isEmpty()){
            for (int i = 0; i < allReviews.size(); i++) {
                if(allReviews.get(i).getIdReview()>= newId){
                    newId = allReviews.get(i).getIdReview() +1;
                }
            }
        }
        return newId;

    }

}
