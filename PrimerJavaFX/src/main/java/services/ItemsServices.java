/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ItemsDao;
import java.util.ArrayList;
import model.Item;

/**
 *
 * @author dam2
 */
public class ItemsServices {
    
    public ArrayList<Item> getAllItems() {
        ItemsDao dao = new ItemsDao();
        return dao.getItems();
    }
}
