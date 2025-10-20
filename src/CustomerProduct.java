import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct {
    private String CustomerSSN;
    private String ProductID;
    private LocalDate PurchaseDate;
    private boolean paid;


    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public CustomerProduct(String CustomerSSN, String ProductID, LocalDate PurchaseDate) {
        this.CustomerSSN = CustomerSSN;
        this.ProductID = ProductID;
        this.PurchaseDate = PurchaseDate;
        this.paid = false;
    }
    public String getCustomerSSN(){
        return CustomerSSN;
    }
    public String getProductID(){
        return ProductID;
    }
    public LocalDate getPurchaseDate(){
        return PurchaseDate;
    }

    public String lineRepresentation(){
        return CustomerSSN + "," + ProductID + "," + PurchaseDate.format(formatter) + "," + paid;
    }

    public boolean ispaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getSearchKey() {
        return CustomerSSN + "," + ProductID + "," + PurchaseDate.format(formatter);
    }


}