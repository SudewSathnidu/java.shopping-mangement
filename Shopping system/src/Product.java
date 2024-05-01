public abstract class Product {
    private String productId;
    private String productName;
    private int numOfAvailableItems;
    private double price;
    public Product(String productId, String productName, int numOfAvailableItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.numOfAvailableItems = numOfAvailableItems;
        this.price = price;
    }
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getNumOfAvailableItems() {
        return numOfAvailableItems;
    }

    public double getPrice() {
        return price;
    }

    // Setter methods
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNumOfAvailableItems(int numOfAvailableItems) {
        this.numOfAvailableItems = numOfAvailableItems;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
