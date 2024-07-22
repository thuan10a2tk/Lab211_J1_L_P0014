package model;

public class Request {
    private String rID;
    private String assetID, employID;
    private int quantity;
    private String requestDateTime;

    public Request() {
    }

    public Request(String rID, String assetID, String employeeID, int quantity, String requestDateTime) {
        setrID(rID);
        setAssetID(assetID);
        setEmployID(employeeID);
        this.quantity = quantity;
        setRequestDateTime(requestDateTime);
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

    public String getrID() {
        return rID;
    }
    
    public final void setrID(String rID) {
        if(rID.matches("^R\\d{3}$")) this.rID = rID;
        else this.rID = null;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public final void setRequestDateTime(String requestDateTime) {
        this.requestDateTime =requestDateTime;
    }

    @Override
    public String toString() {
        return rID + "," + assetID + "," + employID +  "," + quantity + ","+ requestDateTime;
    }
    
    
}
