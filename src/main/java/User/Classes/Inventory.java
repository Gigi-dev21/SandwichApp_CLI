package User.Classes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

//Loads the inventory.json
//Jackson uses the JSON keys to match field names with Inventory class and automatically assigns the values accordingly.
public class Inventory {
    public Map<String, Map<String, Double>> bread;
    public Map<String, Map<String, Double>> meat;
    public Map<String, Map<String, Double>> cheese;
    public Map<String, String> regular_toppings;
    public Map<String, String> sauces;
    public Map<String, Map<String, Double>> drinks;
    public Map<String, Double> chips;
    public Map<String, String> sides;
    public Map<String, SignatureSandwich> signature_sandwiches;

    public static Inventory loadFromFile(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filePath), Inventory.class);
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
            return null;
        }
    }
}
