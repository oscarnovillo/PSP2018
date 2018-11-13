/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
//import configuration.ConfigProperties;
//import fx.controllers.purchases.FXMLPurchasesController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Review;


/**
 *
 * @author Laura
 */
public class ReviewsDao {
    
    // esta clase no hace falta por ser desde un xml y con customers y reviews anidados.
    
    public ArrayList<Customer> loadReviews() {

        ArrayList<Customer> reviews = new ArrayList();
//        Path file = Paths.get(ConfigProperties.getInstance().getProperty("reviews"));
//        Charset charset = Charset.forName("UTF-8");
//        BufferedReader reader = null;
//
//        XStream xs = new XStream(new DomDriver());
//
//        xs.alias("customer", Customer.class);
//        xs.alias("review", Review.class);
//
//        xs.aliasField("id", Customer.class, "idCustomer");
//        xs.aliasField("item", Review.class, "itemId");
//        xs.addImplicitCollection(Customer.class, "reviews");
//
//        xs.useAttributeFor(Customer.class, "idCustomer");
//        xs.useAttributeFor(Review.class, "itemId");
//        
//        
//
//        try {
//            reader = Files.newBufferedReader(file, charset);
//            reviews = (ArrayList<Customer>) xs.fromXML(reader);
//
//        } catch (IOException x) {
//            System.err.format("IOException: %s%n", x);
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(FXMLPurchasesController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
        return reviews;
    }

    public void saveReviews(ArrayList<Customer> reviews) {
//        Path file = Paths.get(ConfigProperties.getInstance().getProperty("reviews"));
//        Charset charset = Charset.forName("UTF-8");
//
//        XStream xs = new XStream(new DomDriver());
//
//        xs.alias("customer", Customer.class);
//        xs.alias("review", Review.class);
//
//        xs.aliasField("id", Customer.class, "idCustomer");
//        xs.aliasField("item", Review.class, "itemId");
//        xs.addImplicitCollection(Customer.class, "reviews");
//
//        xs.useAttributeFor(Customer.class, "idCustomer");
//        xs.useAttributeFor(Review.class, "itemId");
//
//        String xml = xs.toXML(reviews);
//
//        //Creates a BufferedWriter (java.io) in a more efficient way using Files from java.nio
//        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
//            writer.write(xml);
//            writer.close();
//        } catch (IOException x) {
//            System.err.format("IOException: %s%n", x);
//        }
    }
}
