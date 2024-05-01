import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {

    private static List<Product> products; //list for products
    public WestminsterShoppingManager(){
        products = new ArrayList<>();
        loadProductsFromFile();
    }
//add product
@Override
    public void addProduct(Product product) {
        products.add(product);
    }
// deleting products from system
@Override
    public void deleteProduct(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                products.remove(product);
                return;
            }
        }
    }

//printing the products
    @Override
    public void printProducts() {
        for (Product product : products) {
            // Print common product details
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Number of Available Items: " + product.getNumOfAvailableItems());
            System.out.println("Price: " + product.getPrice());
// additional details for electronics and the clothing
            if (product instanceof Electronics electronic) {
                System.out.println("Type: Electronics");
                System.out.println("Brand: " + electronic.getBrand());
                System.out.println("Warranty Period: " + electronic.getWarrantyPeriod());
            } else if (product instanceof Clothing clothing) {
                System.out.println("Type: Clothing");
                System.out.println("Size: " + clothing.getSize());
                System.out.println("Color: " + clothing.getColor());
            } else {
                System.out.println("Type: Unknown");
            }
            System.out.println("-------------------------------------------");
        }
    }


    public int getProductCount() {
        return products.size();
    }
    @Override
    public void saveProductsToFile() { //saveing the product for the text file
        String filename = "products.txt";
        try {
            //writingdown  the file
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write("ProductID,ProductName,AvailableItems,Price,Type,SpecificDetails\n");
            for (Product p : products) {
                fileWriter.write(p.getProductId() + ",");
                fileWriter.write(p.getProductName() + ",");
                fileWriter.write(p.getNumOfAvailableItems() + ",");
                fileWriter.write(p.getPrice() + ",");
                //write the electronics and clothing seperatly
                if (p instanceof Electronics) {
                    Electronics e = (Electronics) p;
                    fileWriter.write("Electronics,");
                    fileWriter.write("Brand:" + e.getBrand() + ";WarrantyPeriod:" + e.getWarrantyPeriod() + "\n");
                } else if (p instanceof Clothing) {
                    Clothing c = (Clothing) p;
                    fileWriter.write("Clothing,");
                    fileWriter.write("Size:" + c.getSize() + ";Color:" + c.getColor() + "\n");
                } else {
                    fileWriter.write("Unknown Type\n");
                }
            }

            fileWriter.close();
        }
        catch(IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
//loaded products from the file
    private static void loadProductsFromFile() {
        String filename = "products.txt";
//read the all products
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");

                String productId = details[0];
                String productName = details[1];
                int availableItems = Integer.parseInt(details[2]);
                double price = Double.parseDouble(details[3]);
                String type = details[4];

                if ("Electronics".equals(type)) {
                    String[] specificDetails = details[5].split(";");
                    String brand = specificDetails[0].split(":")[1];
                    String warrantyPeriod = specificDetails[1].split(":")[1];
                    Electronics electronic = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
                    products.add(electronic);
                } else if ("Clothing".equals(type)) {
                    String[] specificDetails = details[5].split(";");
                    String size = specificDetails[0].split(":")[1];
                    String color = specificDetails[1].split(":")[1];
                    Clothing clothing = new Clothing(productId, productName, availableItems, price, size, color);
                    products.add(clothing);
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number from file: " + e.getMessage());
        }
    }

    public List<Product> getProducts(){
        return products;
    }




}
