import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> listOfProducts;
    private boolean isFirstPurchase = true;

    public ShoppingCart() {
        listOfProducts = new ArrayList<>();
    }

    public void addProduct(Product product) {
        listOfProducts.add(product);
    }

    public void removeProduct(Product product) {
        listOfProducts.remove(product);
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Product product : listOfProducts) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    public void clearCart() {
        listOfProducts.clear();
    }

    public ArrayList<Product> getProducts() {
        return listOfProducts;
    }

    public void markAsPurchased() {
        isFirstPurchase = false;
    }
    public boolean findProductById(String productId) {
        for (Product product : listOfProducts) {
            if (product.getProductId().equals(productId)) {
                return true;
            }
        }
        return false;
    }
}
