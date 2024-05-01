public class Electronics extends Product{
    private String brand;
    private String warrantyPeriod;

    public Electronics(String productId, String productName, int numOfAvailableItems, double price, String brand, String warrantyPeriod) {
        super(productId, productName, numOfAvailableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }
    public String getBrand(){
        return this.brand;
    }
    public String getWarrantyPeriod(){
        return this.warrantyPeriod;
    }
    public void setBrand(String brand){
        this.brand=brand;
    }
    public void setWarrantyPeriod(String warrantyPeriod){
        this.warrantyPeriod=warrantyPeriod;
    }
}
