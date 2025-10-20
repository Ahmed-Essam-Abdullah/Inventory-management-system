import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Test {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("Welcome to Inventory Management System");
        System.out.println("=========================================");

        while (true) {
            System.out.println("\nSelect user type:");
            System.out.println("1. Admin");
            System.out.println("2. Employee");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    employeeMenu();
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }

    // ===================================
    // ADMIN MENU
    // ===================================
    private static void adminMenu() {
        AdminRole admin = new AdminRole();

        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add New Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Show All Employees");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    String id = input.nextLine();
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Email: ");
                    String email = input.nextLine();
                    System.out.print("Enter Address: ");
                    String address = input.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = input.nextLine();

                    admin.addEmployee(id, name, email, address, phone);
                    System.out.println("Employee added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Employee ID to remove: ");
                    String removeId = input.nextLine();
                    admin.removeEmployee(removeId);
                    System.out.println("Employee removed (if found).");
                    break;

                case 3:
                    EmployeeUser[] employees = admin.getListOfEmployees();
                    System.out.println("\n--- Employee List ---");
                    for (EmployeeUser e : employees) {
                        System.out.println(e.lineRepresentation());
                    }
                    break;

                case 4:
                    admin.logout();
                    System.out.println("Admin logged out. Data saved.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ===================================
    // EMPLOYEE MENU
    // ===================================
    private static void employeeMenu() {
        EmployeeRole emp = new EmployeeRole();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("\n=== Employee Menu ===");
            System.out.println("1. Add New Product");
            System.out.println("2. Show All Products");
            System.out.println("3. Sell Product to Customer");
            System.out.println("4. Return Product");
            System.out.println("5. Show All Purchases");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String pid = input.nextLine();
                    System.out.print("Enter Product Name: ");
                    String pname = input.nextLine();
                    System.out.print("Enter Manufacturer Name: ");
                    String mname = input.nextLine();
                    System.out.print("Enter Supplier Name: ");
                    String sname = input.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = input.nextInt();
                    System.out.print("Enter Price: ");
                    float price = input.nextFloat();
                    input.nextLine();

                    emp.addProduct(pid, pname, mname, sname, qty);
                    System.out.println("Product added successfully!");
                    break;

                case 2:
                    Product[] products = emp.getListOfProducts();
                    System.out.println("\n--- Product List ---");
                    for (Product p : products) {
                        System.out.println(p.lineRepresentation());
                    }
                    break;

                case 3:
                    System.out.print("Enter Customer SSN: ");
                    String ssn = input.nextLine();
                    System.out.print("Enter Product ID: ");
                    String prID = input.nextLine();
                    System.out.print("Enter Purchase Date (dd-MM-yyyy): ");

                    LocalDate pDate = LocalDate.parse(input.nextLine(), formatter);

                    boolean purchased = emp.purchaseProduct(ssn, prID, pDate);
                    if (purchased) {
                        System.out.println("Product purchased successfully!");
                    } else {
                        System.out.println("Purchase failed (maybe quantity = 0).");
                    }
                    break;

                case 4:
                    System.out.print("Enter Customer SSN: ");
                    String ssnR = input.nextLine();

                    System.out.print("Enter Product ID: ");
                    String prR = input.nextLine();

                    try {
                        System.out.print("Enter Purchase Date (dd-MM-yyyy): ");
                        String purchaseInput = input.nextLine();
                        LocalDate pd = LocalDate.parse(purchaseInput, formatter);

                        System.out.print("Enter Return Date (dd-MM-yyyy): ");
                        String returnInput = input.nextLine();
                        LocalDate rd = LocalDate.parse(returnInput, formatter);

                        double refunded = emp.returnProduct(ssnR, prR, pd, rd);
                        if (refunded == -1) {
                            System.out.println("Return rejected.");
                        } else {
                            System.out.println("Return accepted. Refunded: " + refunded);
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use dd-MM-yyyy (e.g., 10-10-2024).");
                    }
                    break;


                case 5:
                    CustomerProduct[] purchases = emp.getListOfPurchasingOperations();
                    System.out.println("\n--- Customer Purchases ---");
                    for (CustomerProduct c : purchases) {
                        System.out.println(c.lineRepresentation());
                    }
                    break;

                case 6:
                    emp.logout();
                    System.out.println("Employee logged out. Data saved.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}