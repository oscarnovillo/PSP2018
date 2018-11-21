/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import model.Item;

/**
 *
 * @author oscar
 */
public class TestLocalDate {
    public static void main(String[] args) throws JsonProcessingException, IOException {
        Item i = new Item(1,"n", "c", 0);
        System.out.println(i);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
//module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
mapper.registerModule(module);
        String s = mapper.writeValueAsString(i);
        System.out.println(s);
        i = mapper.readValue(s, new TypeReference<Item>() { });
        System.out.println(i);
        
    }
    
}
