/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CustomerProductDatabase {

    private ArrayList<CustomerProduct> records;
    private String filename;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public CustomerProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public void readFromFile() {
        records.clear(); // clear old data
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(createRecordFrom(line));
            }
        } catch (IOException e) {
            System.out.println("Can't read file");
        }
    }

    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.split(",");
        LocalDate date = LocalDate.parse(parts[2], formatter);
        CustomerProduct cp = new CustomerProduct(parts[0], parts[1], date);
        cp.setPaid(Boolean.parseBoolean(parts[3]));
        return cp;
    }

    public ArrayList<CustomerProduct> returnAllRecords() {
        return records;
    }
 public boolean contains(String key) {
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public CustomerProduct getRecord(String key) {
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                return cp;
            }
        }
        return null;
    }

    public void insertRecord(CustomerProduct record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        records.removeIf(cp -> cp.getSearchKey().equals(key));
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (CustomerProduct cp : records) {
                pw.println(cp.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Error saving to the file");
        }
    }
}