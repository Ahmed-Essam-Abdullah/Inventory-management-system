import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeUserDatabase extends Database {

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public Object createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
            return new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
        return null;
    }

    @Override
    public boolean isMatch(Object record, String key) {
        if (record instanceof EmployeeUser e) {
            return e.getSearchKey().equals(key);
        }
        return false;
    }

    @Override
    public ArrayList<Object> returnAllRecords() {
        readFromFile();
        return new ArrayList<>(records);
    }

    @Override
    public void saveToFile() {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Object obj : records) {
                EmployeeUser record = (EmployeeUser) obj;
                writer.write(record.lineRepresentation() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error in saving the file");
        }
    }
}
