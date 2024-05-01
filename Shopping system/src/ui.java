import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Objects;
//loaded data related to the
public class ui extends JFrame {
    private JPanel ShoppinCart;
    private JButton shoppingCartButton;
    private JComboBox<String> comboBox1;
    private JTable table1;
    private JButton addToCart;
    private JLabel detailsLabel;
    private DefaultTableModel tableModel;

    private static final WestminsterShoppingManager mg = new WestminsterShoppingManager();

    private static List<Product> products;

    private final ShoppingCart shoppingCart = new ShoppingCart();

    public ui() {
        super("Online Shopping System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ShoppinCart);
        products = mg.getProducts();
        loadTableData("All");
        table1.setModel(tableModel);

        mg.printProducts();

        comboBox1.addItem("All");
        comboBox1.addItem("Electronics");
        comboBox1.addItem("Clothing");

        comboBox1.addActionListener(e -> {
            String selectedCategory = (String) comboBox1.getSelectedItem();
            System.out.println(selectedCategory);
            tableModel.setRowCount(0);
            loadTableData(selectedCategory);
            table1.setModel(tableModel);
        });

        table1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showSelectedProductDetails();
            }
        });
        table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String productId = table.getModel().getValueAt(row, 0).toString();
                Product product = findProductById(productId);
                if (product != null) {
                    // Now, check the product's availability and set color accordingly
                    if (product.getNumOfAvailableItems() < 3) {
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(Color.BLACK); // or any default color
                    }
                } else {
                    c.setForeground(Color.BLACK); // default color if product is not found
                }
                return c;
            }
        });


        addToCart.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow >= 0) {
                // Retrieve the selected product. Assume product id is in column 0
                String productId = table1.getValueAt(selectedRow, 0).toString();
                Product selectedProduct = findProductById(productId); // Implement this method to find product by ID
                if (selectedProduct != null) {
                    shoppingCart.addProduct(selectedProduct);
                    JOptionPane.showMessageDialog(this, "Added to cart: " + selectedProduct.getProductName());
                }
            } else {
                JOptionPane.showMessageDialog(this, "No product selected!");
            }
        });
        shoppingCartButton.addActionListener(e -> {
            Cart cartUI = new Cart(shoppingCart);
//            this.setVisible(false);
            cartUI.setVisible(true);
        });
        this.pack();

    }

    public static void main(String[] args) {
        try {
            ui frame = new ui();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null; // Return null if no product found with the given ID
    }

    private void loadTableData(String category) {
        String[] dataValues = new String[5];
        String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Price");
        tableModel.addColumn("Info");
        tableModel.addRow(columnNames);

        for (Product product : products) {
            String productCategory = (product instanceof Electronics) ? "Electronics" : (product instanceof Clothing) ? "Clothing" : "Unknown";
            if (Objects.equals(category, "All")) {

                dataValues[0] = product.getProductId();
                dataValues[1] = product.getProductName();
                dataValues[2] = (product instanceof Electronics) ? "Electronics" : (product instanceof Clothing) ? "Clothing" : "Unknown";
                dataValues[3] = String.valueOf(product.getNumOfAvailableItems());
                if (product instanceof Electronics elec) {
                    String data = elec.getBrand();
                    data += ",";
                    data += elec.getWarrantyPeriod();
                    dataValues[4] = data;
                } else if (product instanceof Clothing cloth) {
                    String data = cloth.getColor();
                    data += ",";
                    data += cloth.getSize();
                    dataValues[4] = data;
                }
                tableModel.addRow(dataValues);
            } else if (Objects.equals(category, "Electronics")) {
                if (productCategory.equals("Electronics")) {
                    dataValues[0] = product.getProductId();
                    dataValues[1] = product.getProductName();
                    dataValues[2] = "Electronics";
                    dataValues[3] = String.valueOf(product.getNumOfAvailableItems());
                    Electronics elec = (Electronics) product;
                    String data = elec.getBrand();
                    data += ",";
                    data += elec.getWarrantyPeriod();
                    dataValues[4] = data;
                    tableModel.addRow(dataValues);
                }
            } else if (Objects.equals(category, "Clothing")) {
                if (productCategory.equals("Clothing")) {
                    dataValues[0] = product.getProductId();
                    dataValues[1] = product.getProductName();
                    dataValues[2] = "Clothing";
                    dataValues[3] = String.valueOf(product.getNumOfAvailableItems());
                    Clothing cloth = (Clothing) product;
                    String data = cloth.getColor();
                    data += ",";
                    data += cloth.getSize();
                    dataValues[4] = data;
                    tableModel.addRow(dataValues);
                }
            }
        }
    }

    private void showSelectedProductDetails() {
        Product pr;
        int selectedRow = table1.getSelectedRow();
        if (selectedRow >= 0) {
            String information = (String)table1.getValueAt(selectedRow, 4);
            String[] DataInfo = information.split(",");
            String productID = (String)table1.getValueAt(selectedRow, 0);
            int itemsCount = 0;
            Product pTemp = findProductById(productID);
            itemsCount = pTemp.getNumOfAvailableItems();
            String details = "<html>Seleted Products Details <br/> Product ID: " + table1.getValueAt(selectedRow, 0)
                    + "<br/>Category: " + table1.getValueAt(selectedRow, 2)
                    + "<br/>Name: " + table1.getValueAt(selectedRow, 1)
                    + "<br/>Price: " + table1.getValueAt(selectedRow, 3) + "<br/>";
            if(table1.getValueAt(selectedRow, 2).equals("Clothing")){
                details += "Size: " + DataInfo[0] + " <br/>";
                details += "Color: " + DataInfo[1] + "<br/>";

            }else if(table1.getValueAt(selectedRow, 2).equals("Electronics")){
                details += "Brand: " + DataInfo[0] + " <br/>";
                details += "Warranty Period: " + DataInfo[1] + "<br/>";

            }
            details += "Items Available: " + itemsCount;
            details += "</html>";

            detailsLabel.setText(details);
        }
    }
}