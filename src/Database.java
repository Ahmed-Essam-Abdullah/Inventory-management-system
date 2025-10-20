import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Database{
    ArrayList<Object> records = new ArrayList<>();
    String filename;

    public Database(String filename) {
        this.filename = filename;
    }

    public abstract Object createRecordFrom(String line);

    public abstract boolean isMatch(Object record, String key);

    public void readFromFile() {
        FileReader fileReader = null;
        records.clear();
        try {
            fileReader = new FileReader(filename);
            StringBuilder lineBuilder = new StringBuilder();
            int ch;

            while ((ch = fileReader.read()) != -1) {
                if (ch == '\n') {
                    String line = lineBuilder.toString().trim();

                    if (!line.isEmpty()) {

                        Object record = createRecordFrom(line);
                        if (record != null) records.add(record);
                    }
                    lineBuilder.setLength(0);
                } else {
                    lineBuilder.append((char) ch);
                }
            }

            if (lineBuilder.length() > 0) {
                String line = lineBuilder.toString().trim();
                if (!line.isEmpty()) {
                    Object record = createRecordFrom(line);
                    if (record != null) records.add(record);
                }
            }

        } catch (IOException e) {
            System.out.println("Error in reading yhe file");
        } finally {
            try {
                if (fileReader != null) fileReader.close();
            } catch (IOException e) {
                System.out.println("Error in closing the file");
            }
        }
    }

    public ArrayList<Object> returnAllRecords() {
        readFromFile();
        return records;
    }

    public boolean contains(String key) {
    for (int i = 0; i < records.size(); i++) {
        Object record = records.get(i);
        if (isMatch(record, key)) {
            return true;
        }
    }
    return false;
}

public Object getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            Object record = records.get(i);
            if (isMatch(record, key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(Object record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        Object toBeRemoved = null;
        for (int i = 0; i < records.size(); i++) {
            Object record = records.get(i);
            if (isMatch(record, key)) {
                toBeRemoved = record;
                break;
            }
        }
        if (toBeRemoved != null) records.remove(toBeRemoved);
    }

    public void saveToFile() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            for (int i = 0; i < records.size(); i++) {
                Object record = records.get(i);
                writer.write(record.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error in saving the file");
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error in closing the file" );
            }
        }
    }
    // Add this method inside your Database (or CustomerProductDatabase) class

    public boolean valid(String customerSSN, String productID, LocalDate purchaseDate) {
        for (Object recordObj : records) {
            if (recordObj instanceof CustomerProduct) {
                CustomerProduct record = (CustomerProduct) recordObj;

                if (record.getCustomerSSN().equals(customerSSN)
                        && record.getProductID().equals(productID)
                        && record.getPurchaseDate().equals(purchaseDate)) {
                    return true;
                }
            }
        }
        return false;
    }
}