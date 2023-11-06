package com.engr.oufoodpantry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InventoryService { 
    private static final String JSON_FILE_PATH = "C:/testdata/items.json"; // New file path

    public static List<Item> readItemsFromJson() { // This method reads the JSON file and returns an ArrayList
        List<Item> items = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(JSON_FILE_PATH))) {
                Files.createDirectories(Paths.get(JSON_FILE_PATH).getParent());
                Files.createFile(Paths.get(JSON_FILE_PATH));
            }
            try (InputStream is = new FileInputStream(JSON_FILE_PATH)) {
                JsonReader reader = Json.createReader(is);
                JsonArray jsonArray = reader.readArray();

                for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                    String name = jsonObject.getString("name");
                    int amount = jsonObject.getInt("amount");
                    items.add(new Item(name, amount));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items; 
        /*NOTE: items is an ArrayList. the format looks like this:
         * -Item(name="eggs", amount = 20)
         * -Item(name="milk", amount = 3)
         * To access these elements, a for loop can be used like this:
         * List<Item> items = InventoryService.readItemsFromJson();
         * for(Item item : items) {
         * System.out.println("Name: " + item.getName() + ", Amount: " + item.getAmount());}
         * 
         * This would print: 
         * Name: eggs, Amount: 20
         * Name: milk, Amount: 3
         * Using the values I put in the comments above
         * 
         * To get a specific element of the ArrayList, for example just the second item which is milk in my example:
         * Item milkItem = items.get(1);
         * System.out.println("Name: " + milkItem.getName() + ", Amount: " + milkItem.getAmount());
         * 
          */

    }
    public static void addItemToJson(String itemName) {
        List<Item> items = readItemsFromJson();
        items.add(new Item(itemName, 0)); 
        writeItemsToJson(items); 
    }
    public static void deleteItemFromJson(String itemName) {
        List<Item> items = readItemsFromJson();
        items.removeIf(item -> item.getName().equals(itemName));
        writeItemsToJson(items);
    }
    
    private static void writeItemsToJson(List<Item> items) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Item item : items) {
            jsonArrayBuilder.add(Json.createObjectBuilder()
                    .add("name", item.getName())
                    .add("amount", item.getAmount()));
        }
        JsonArray jsonArray = jsonArrayBuilder.build();
    
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.writeArray(jsonArray);
        }
    
        try {
            
            try (FileOutputStream fos = new FileOutputStream(JSON_FILE_PATH)) {
                fos.write(stringWriter.toString().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateItemAmountInJson(String itemName, int newAmount) {
        List<Item> items = readItemsFromJson();
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                item.setAmount(newAmount);
                break; 
            }
        }

        writeItemsToJson(items); 
    }
}

  



