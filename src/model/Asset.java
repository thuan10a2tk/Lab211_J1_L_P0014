
package model;

public class Asset {
    private String assetID, name, color;
    private double price, weight;
    private int quantity;

    public Asset() {
    }

    public Asset(String assetID, String name, String color, double price, double weight, int quantity) {
        setAssetID(assetID);
        this.name = name;
        this.color = color;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
    }

    public String getAssetID() {
        return assetID;
    }

    public final void setAssetID(String assetID) {
        if(assetID.matches("^A\\d{3}$"))
            this.assetID = assetID;
        else this.assetID = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.equals(""))
        this.name = name;
    }

    public String getColor() {
        
        return color;
    }

    public void setColor(String color) {
        if(!color.equals(""))
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        if(!price.equals(""))
        this.price = Double.parseDouble(price);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        if(!weight.equals(""))
            this.weight = Double.parseDouble(weight);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        if(!quantity.equals(""))
        this.quantity = Integer.parseInt(quantity);
    }

    @Override
    public String toString() {
        return assetID + "," + name + "," + color +  "," + price + ","+ weight +"," +quantity;
    }
    
    
}
