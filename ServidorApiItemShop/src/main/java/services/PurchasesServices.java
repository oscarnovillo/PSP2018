/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PurchasesDao;
import java.util.ArrayList;
import model.Purchase;

/**
 *
 * @author dam2
 */
public class PurchasesServices {

    public ArrayList<Purchase> getAllPurchases() {
        PurchasesDao dao = new PurchasesDao();
        return dao.loadPurchasesList();
    }

    public ArrayList<Purchase> searchByDate(String date) {
        PurchasesDao dao = new PurchasesDao();
        ArrayList<Purchase> purchasesDate = new ArrayList();
        ArrayList<Purchase> purchases = dao.loadPurchasesList();

        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getDate().equalsIgnoreCase(date)) {
                purchasesDate.add(purchases.get(i));
            }
        }
        return purchasesDate;
    }

    public ArrayList<Purchase> getPurchasesByClientId(int id) {
       PurchasesDao dao = new PurchasesDao();
        ArrayList<Purchase> purchases = dao.loadPurchasesList();
        ArrayList<Purchase> clientPurchases = new ArrayList();
        for (int i = 0; i < purchases.size(); i++) {
            if(purchases.get(i).getIdCustomer() == id){
                clientPurchases.add(purchases.get(i));
            }
        }
        return clientPurchases;
    }

    public void deletePurchase(Purchase purchase) {
        PurchasesDao dao = new PurchasesDao();
        ArrayList<Purchase> purchases = dao.loadPurchasesList();
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getIdPurchase() == purchase.getIdPurchase()) {
                purchases.remove(purchases.get(i));
            }
        }

        dao.savePurchases(purchases);
    }

    public Purchase addPurchase(String customerId, String itemId, String date) {
        PurchasesDao dao = new PurchasesDao();
        ArrayList<Purchase> purchases = dao.loadPurchasesList();
        String item = itemId.split(" ")[0];
        String customer = customerId.split(" - ")[0];
        int generatedId = generateNewId();
        Purchase newPurchase = new Purchase(generatedId , Integer.parseInt(customer), item, date);
        purchases.add(newPurchase);
        dao.savePurchases(purchases);

        return newPurchase;
    }

    public boolean validateID(String text) {
        return text.matches("[0-9]*");
    }

    public boolean checkPurchaseID(String id) {
        PurchasesDao dao = new PurchasesDao();
        ArrayList<Purchase> purchases = dao.loadPurchasesList();
        boolean repeatedID = false;
        for (int i = 0; i < purchases.size(); i++) {
            if (Integer.parseInt(id) == purchases.get(i).getIdPurchase()) {
                repeatedID = true;
            }
        }
        return repeatedID;
    }
    
        public int generateNewId(){
        PurchasesDao dao = new PurchasesDao();
        ArrayList<Purchase> allPurchases = dao.loadPurchasesList();
    
        int newId = 1;
        if(!allPurchases.isEmpty()){
            for (int i = 0; i < allPurchases.size(); i++) {
                if(allPurchases.get(i).getIdPurchase() >= newId ){
                    newId = allPurchases.get(i).getIdPurchase() +1;
                }
            }
        }
        return newId;

    }
}
