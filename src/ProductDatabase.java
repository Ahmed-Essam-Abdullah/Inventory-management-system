import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author AliAl
 */
public class ProductDatabase extends Database {

    private ArrayList<Product> records = new ArrayList<>();

    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Object createRecordFrom(String line) {
        String[] parts = line.split(",");
    if (parts.length == 6) {
        String productID = parts[0];
        String productName = parts[1];
        String manufacturerName = parts[2];
        String supplierName = parts[3];
        int quantity = Integer.parseInt(parts[4]);
        float price = Float.parseFloat(parts[5]);
        return new Product(productID, productName, manufacturerName, supplierName, quantity, price);
    }
    return null;
    }

    @Override
    public boolean isMatch(Object record, String key) {
        if (record instanceof Product) {
            return ((Product) record).getSearchKey().equals(key);
        }
        return false;
    }

    @Override
    public ArrayList<Object> returnAllRecords() {
        return new ArrayList<>(records);
    }
}
