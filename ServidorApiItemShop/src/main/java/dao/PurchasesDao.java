/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import configuration.ConfigProperties;

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
import model.Purchase;

/**
 *
 * @author Laura
 */
public class PurchasesDao {
    
    public ArrayList<Purchase> loadPurchasesList() {

        ArrayList<Purchase> purchases = new ArrayList();
//        Path file = Paths.get(ConfigProperties.getInstance().getProperty("purchases"));
//        Charset charset = Charset.forName("UTF-8");
//        BufferedReader reader = null;
//
//        try {
//            reader = Files.newBufferedReader(file, charset);
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                String[] lineDivided = line.split(";");
//                purchases.add(new Purchase(Integer.parseInt(lineDivided[0]), Integer.parseInt(lineDivided[1]), lineDivided[2], lineDivided[3]));
//            }
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
        return purchases;
    }
    
    public void savePurchases(ArrayList<Purchase> purchases) {
//        Path file = Paths.get(ConfigProperties.getInstance().getProperty("purchases"));
//        Charset charset = Charset.forName("UTF-8");
//
//        //Creates a BufferedWriter (java.io) in a more efficient way using Files from java.nio
//        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
//            for (int i = 0; i < purchases.size(); i++) {
//                writer.write(purchases.get(i).toStringTexto());
//                //Writes a newLine for carriage return and linefeed
//                if (i < purchases.size() - 1) {
//                    writer.newLine();
//                }
//            }
//            writer.close();
//        } catch (IOException x) {
//            System.err.format("IOException: %s%n", x);
//        }
    }
    
    
}
