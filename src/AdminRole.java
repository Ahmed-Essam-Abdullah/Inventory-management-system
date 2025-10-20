import java.util.ArrayList;

public class AdminRole {
    private EmployeeUserDatabase database;
    public AdminRole(){
        this.database = new EmployeeUserDatabase("Employees.txt");
        database.readFromFile();
    }

    public void addEmployee(String employeeId, String name, String email, String
            address, String phoneNumber){
        if(database.contains(employeeId)){
            System.out.println("Employee is already exists");
            return;
        }
        EmployeeUser e = new EmployeeUser(employeeId,name,email,address,phoneNumber);
        database.insertRecord(e);
        database.saveToFile();
        System.out.println("Your employee is added successfully");
    }
    public EmployeeUser[] getListOfEmployees(){
        ArrayList<Object> records =database.returnAllRecords();
        return records.toArray(new EmployeeUser[0]);
    }
    public void removeEmployee(String key){
        if(!database.contains(key)){
            System.out.println("Employee doesn't exist");
            return;
        }
        database.deleteRecord(key);
        database.saveToFile();
        System.out.println("Your employee is removed successfully");
    }
    public void logout(){
        database.saveToFile();
        System.out.println("logout successfully.. ");
    }
}

