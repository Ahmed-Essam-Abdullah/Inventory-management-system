import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CustomerProductDatabase extends Database {

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Object createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            String customerSSN = parts[0];
            String productID = parts[1];
            LocalDate purchaseDate = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
            cp.setPaid(Boolean.parseBoolean(parts[3]));
            return cp;
        }
        return null;
    }

    @Override
    public boolean isMatch(Object record, String key) {
        if (record instanceof CustomerProduct cp) {
            return cp.getSearchKey().equals(key);
        }
        return false;
    }

    @Override
    public boolean contains(String key) {
        for (Object record : records) {
            if (isMatch(record, key)) return true;
        }
        return false;
    }

    @Override
    public void saveToFile() {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Object obj : records) {
                CustomerProduct record = (CustomerProduct) obj;
                writer.write(record.lineRepresentation() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error in saving the file");
        }
    }
}
