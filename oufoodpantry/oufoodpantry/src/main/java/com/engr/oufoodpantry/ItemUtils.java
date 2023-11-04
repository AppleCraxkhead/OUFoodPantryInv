package com.engr.oufoodpantry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.InputStream;
import java.util.List;
import java.util.Collections;


public class ItemUtils {
    public static List<Item> readItemsFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = ItemUtils.class.getResourceAsStream("/items.json")) {
            return objectMapper.readValue(is, new TypeReference<List<Item>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
