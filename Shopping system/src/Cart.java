import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Cart extends JFrame {
    private JTable table1;
    private JPanel mainFrame;
    private JLabel finalValue;
    private DefaultTableModel tableModel;
    private final HashMap<Product,Integer> productsTemp = new HashMap<Product , Integer>();
    public Cart(ShoppingCart sc) {
        // Set up the frame
        super("Online Shopping System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainFrame);
        String[] columnNames = {"Product Details", "Quantity", "Price"};
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product Details");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Price");
        tableModel.addRow(columnNames);
        loadCartData(sc);
        table1.setModel(tableModel);
        printFinalValues();


    }
//i take testing for this method
    public static void main(String[] args) {
        ShoppingCart shopping = new ShoppingCart();
        try {
            Cart CartFrame = new Cart(shopping);
            CartFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//loading data to cart
    private void loadCartData(ShoppingCart sc) {


        for (Product prod : sc.getProducts()) {
            int cnt = 0;
            for(Product tempProd: sc.getProducts()){
                if(sc.findProductById(tempProd.getProductId())) {
                    cnt++;
                }
            }
            productsTemp.put(prod , cnt);
        }
        for (Map.Entry<Product, Integer> entry : productsTemp.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            double price = product.getPrice();
            double total = price * quantity;  // Total price for the quantity

            // Add a row to the table for each product
            tableModel.addRow(new Object[]{
                    product.getProductName(),
                    quantity,
                    String.format("%.2f", total)
            });
        }
    }


    private void printFinalValues(){
        int elecCnt = 0, clothCnt = 0;
        double grandTotal = 0.0;
        for (Map.Entry<Product, Integer> entry : productsTemp.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            double price = product.getPrice();
            double total = price * quantity;
            grandTotal += total;
            
            if(product instanceof Electronics){
                elecCnt++;
            }else if(product instanceof Clothing){
                clothCnt++;
            }


        }
        double categoryDiscount = (elecCnt >= 3 || clothCnt >= 3) ? grandTotal * 0.2 : 0.0;

        grandTotal -= categoryDiscount;

        // Format the final values
        String finalText = "<html>Total: " + String.format("%.2f €", grandTotal + categoryDiscount)
                + "<br/>Three Items in same Category Discount (20%): - " + String.format("%.2f €", categoryDiscount)
                + "<br/>Final Total: " + String.format("%.2f €", grandTotal)
                + "</html>";

        finalValue.setText(finalText);
    }

}
