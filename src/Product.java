
import java.awt.PrintGraphics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AliAl
 */
public class Product {
    private String productID;
    private String productName; 
    private String manufacturerName; 
    private String supplierName;
    private int quantity ;
    private float price = 0.0f; ;
    
    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
    this.productID=productID;
    this.productName=productName; 
    this.manufacturerName=manufacturerName; 
    this.supplierName=supplierName;
    this.quantity =quantity;
   this.price=price ;
}
    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity) {
        this.productID=productID;
        this.productName=productName;
        this.manufacturerName=manufacturerName;
        this.supplierName=supplierName;
        this.quantity =quantity;
        this.price=0;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String lineRepresentation(){
       
        return this.productID+","+this.productName+","+this.manufacturerName+","+this.supplierName+","+this.quantity+","+this.price;
    }
    public String getSearchKey() {
        return this.productID;
    }

    public float getPrice() {
        return this.price;
    }


}
