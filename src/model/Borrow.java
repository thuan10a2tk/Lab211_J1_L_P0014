package model;

public class Borrow {
    private String bID,assetID,employID;
    private int quantity;
    private String borrowDateTime;

    public Borrow() {
    }

    public Borrow(String bID, String assetID, String employID, int quantity, String borrowDateTime) {
        setbID(bID);
        setAssetID(assetID);
        setEmployID(employID);
        this.quantity = quantity;
        setBorrowDateTime(borrowDateTime);
    }

    public String getEmployID() {
        return employID;
    }

    public final void setEmployID(String employID) {
        if(employID.matches("^E\\d{6}$")) this.employID = employID;
        else this.employID = null;
    }
    
    public String getAssetID() {
        return assetID;
    }

    public final void setAssetID(String assetID) {
        if(assetID.matches("^A\\d{3}$"))
            this.assetID = assetID;
        else this.assetID = null;
    }

    public String getbID() {
        return bID;
    }

    public final void setbID(String bID) {
        if(bID.matches("^B\\d{3}")) this.bID = bID;
        else this.bID = null;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBorrowDateTime() {
        return borrowDateTime;
    }

    public final void setBorrowDateTime(String borrowDateTime) {
        this.borrowDateTime = borrowDateTime;
    }
    
    @Override
    public String toString() {
        return bID + "," + assetID + "," + employID +  "," + quantity + ","+ borrowDateTime;
    }
    
    
}
