import java.util.Scanner;
import java.io.*;

public class Main {

    private static WestminsterShoppingManager manager = new WestminsterShoppingManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//main consol menu
        while (true) {
            System.out.println("\n*** Shopping System Console Menu ***");
            System.out.println("1. Add a new product");
            System.out.println("2. Delete a product");
            System.out.println("3. Print all products");
            System.out.println("4. Save products to file");
            System.out.println("5. Open GUI");
            System.out.println("6. EXIT");
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    printAllProducts();
                    break;
                case 4:
                    saveProductsToFile();
                    break;
                case 5:
                    openGUI();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
// here is the maximum 50 products only and TAKE USER INPUT FROM HERE
    private static void addProduct() {
        if (manager.getProductCount() >= 50) {
            System.out.println("Maximum number of products reached.");
            return;
        }
        scanner.nextLine();
        System.out.println("Enter Product ID:");
        String productId = scanner.nextLine();
        System.out.println("Enter Product Name:");
        String productName = scanner.nextLine();
        System.out.println("Enter Number of Available Items:");
        int numOfAvailableItems = scanner.nextInt();
        System.out.println("Enter Price:");
        double price = scanner.nextDouble();
//heres the option when user is choose when run the code
        System.out.println("Enter type of product (1 for Electronics, 2 for Clothing):");
        int type = scanner.nextInt();
        scanner.nextLine();
        if (type == 1) {

            System.out.println("Enter Brand:");
            String brand = scanner.nextLine();
            System.out.println("Enter Warranty Period:");
            String warrantyPeriod = scanner.nextLine();
//adding electronics products
            Electronics electronic = new Electronics(productId, productName, numOfAvailableItems, price, brand, warrantyPeriod);
            manager.addProduct(electronic);
            System.out.println("Electronics product added successfully!");
        } else if (type == 2) {

            System.out.println("Enter Size:");
            String size = scanner.nextLine();
            System.out.println("Enter Color:");
            String color = scanner.nextLine();

            // adding clothing products
            Clothing clothing = new Clothing(productId, productName, numOfAvailableItems, price, size, color);
            manager.addProduct(clothing);
            System.out.println("Clothing product added successfully!");
        } else {
            System.out.println("Invalid product type.");
        }

    }
//deleting the products
    private static void deleteProduct() {
        System.out.println("Enter Product Id: ");
        scanner.nextLine();
        String id = scanner.nextLine();
        manager.deleteProduct(id);
    }
//printing the products for user
    private static void printAllProducts() {
        manager.printProducts();
    }
// saving here the use gave profucts
    private static void saveProductsToFile() {
        manager.saveProductsToFile();
    }
//show the GUI
    private static void openGUI (){
        try {
            ui frame = new ui();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
