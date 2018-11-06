/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;



import config.ConfigurationYaml;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;

/**
 *
 * @author Laura
 */
public class ItemsDao {

    public List<Item> getItems() {
        
        ArrayList<Item> itemArray = new ArrayList();

        Path file = Paths.get(ConfigurationYaml.getInstance().getContexto().getRealPath(ConfigurationYaml.getInstance().getFileItems()));
        Charset charset = Charset.forName("UTF-8");
        BufferedReader reader = null;

        try {
            reader = Files.newBufferedReader(file, charset);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] lineDivided = line.split(";");
                itemArray.add(new Item(Integer.parseInt(lineDivided[0]), lineDivided[1], lineDivided[2], Double.parseDouble(lineDivided[3])));

            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(ItemsDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return itemArray;
    }

}
