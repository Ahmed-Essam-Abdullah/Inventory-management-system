import java.util.ArrayList;

public class EmployeeUserDatabase extends Database {
    private ArrayList<EmployeeUser> records = new ArrayList<>();

    public EmployeeUserDatabase(String filename) {
        super("Employees.txt");
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
        if (record instanceof EmployeeUser) {
            return ((EmployeeUser) record).getSearchKey().equals(key);
        }
        return false;
    }

    @Override
    public ArrayList<Object> returnAllRecords() {
        return new ArrayList<>(records);
    }
}
