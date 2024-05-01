public class Clothing extends Product {
    private String size;
    private String colour;

    public Clothing(String productId, String productName, int numOfAvailableItems, double price , String size, String colour ){
        super(productId, productName, numOfAvailableItems, price);
        this.size=size;
        this.colour=colour;
    }
    public String getSize(){
        return this.size;
    }
    public String getColor(){
        return this.colour;
    }
    public void setSize(String size ){
        this.size=size;
    }
    public void setColour(String colour ){
        this.colour= colour;
    }
}
