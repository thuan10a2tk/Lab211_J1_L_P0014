
package controller;

import model.Asset;
import model.AssetList;
import model.Borrow;
import model.BorrowList;
import model.Employee;
import model.EmployeeList;
import model.Request;
import model.RequestList;
import view.Menu;
import view.Utils;

public class AssetManagement extends Menu<String> implements I_FunctionList{

    AssetList al = new AssetList();
    EmployeeList el = new EmployeeList();
    BorrowList bl = new BorrowList();
    RequestList rl = new RequestList();
    Utils utils = new Utils();

    public AssetManagement(String title, String[]mc) {
        super(title, mc);
    }
    
    public static void main(String[] args) {
        final String[] option ={
            "Login",
            "Other - Quit"
        };
        AssetManagement am = new AssetManagement("Asset Management App", option);
        am.run();
    }
    
    
    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                Employee e  = login();
                if(e == null) break;
                if(e.getRole().equals("MA")){
                    manager();
                } else{
                    employee();
                } 
                break;
            default: System.exit(0);
        }
    }
    public void manager(){
        final String[] option = {
            "Search asset by name",
            "Create new asset",
            "Updating asset's information",
            "Approve the request of employee",
            "Show list of borrow asset",
            "Log out"
        };
        Menu<String> menu = new Menu<String>("MANAGER MODE", option) {
            @Override
            public void execute(int n) {
                switch (n) {
                    case 1: searchAssetByName(); break;
                    case 2: createNewAsset(); break;
                    case 3: updateAssetInformation();break;
                    case 4: appoveTheRequestOfEmployee();break;
                    case 5: showListOfBorrowAsset();break;
                    default: running = false; System.out.println(" Logging out... ");break;
                }
            }
        };
        menu.run();
    }
    private void employee() {
        final String[] option = {
            "Search asset by name",
            "Log out"
        };
        Menu<String> menu = new Menu<String>("EMPLOYEE MODE", option) {
            @Override
            public void execute(int n) {
                switch (n) {
                    case 1: searchAssetByName(); break;
                    default: running = false; System.out.println(" Logging out... ");break;
                }
            }
        };
        menu.run();
    }
    @Override
    public Employee login() {
        String id = utils.getName("Enter id: ");
        String password = utils.getName("Enter password: ");
        Employee e = el.login(id, password);
        return e;
    }

    @Override
    public void searchAssetByName() {
        String name = utils.getName("Enter name of asset: ");
        utils.displayAll(al.searchByLambda(p->p.getName().contains(name)));
    }

    @Override
    public void createNewAsset() {
        while(true){
            String assetID = utils.getId("Enter Id for new asset: ");
            if(!al.searchByLambda(p->p.getAssetID().equals(assetID)).isEmpty()||utils.checkID(assetID)==null){
            System.out.println("ID is already exist or ID is invalid!");
            } else {
                String name = utils.getName("Enter name for new asset: ");
                String color = utils.getName("Enter color for new asset: ");
                double price  = utils.getDouble("Enter price for new asset: ");
                double weight = utils.getDouble("Enter weight for new asset: ");
                int quantity = utils.getInt("Enter quantity for new asset: ");
                al.addNewAsset(new Asset(assetID, name, color, price, weight, quantity));
                System.out.println("Add new asset into list sucessful!");
            }
            boolean check = utils.getBoolean("Are you want continue to add new asset (yes/no)? ");
            if(!check) break;
            }
    }

    @Override
    public void updateAssetInformation() {
        while(true){
            String assetID = utils.getId("Enter id of asset: ");
            if(al.searchByLambda(p->p.getAssetID().equals(assetID)).isEmpty()||utils.checkID(assetID)==null){
                System.out.println("ID is invalid or Id is not exits in list");
                continue;
            }
            Asset asset = al.searchByID(assetID);
            System.out.println("Leave blank to keep current value.");
            asset.setColor(utils.getString("Enter new color (current: "+asset.getColor()+" ) : "));
            asset.setPrice(utils.getString("Enter new name (current: "+asset.getPrice()+" ) : "));
            asset.setWeight(utils.getString("Enter new name (current: "+asset.getWeight()+" ) : "));
            asset.setQuantity(utils.getString("Enter new name (current: "+asset.getQuantity()+" ) : "));
            al.saveFile("asset.txt");
            break;
            }
        
        
    }

    @Override
    public void appoveTheRequestOfEmployee() {
         
            utils.displayAll(rl.getList());
            String id = utils.getName("Enter ID request to appove");
            Request re = rl.searchByID(id);
            if(re == null) {
                System.out.println("ID request is not exist"); return;
            }
                    Asset asset = al.searchByID(re.getAssetID());
                    if(asset.getQuantity()<re.getQuantity()) {
                        System.out.println("Can't aprove this request(Not enough quantity)"); return;
                    }
                    asset.setQuantity(String.valueOf(asset.getQuantity()-re.getQuantity()));
                    rl.delete(id);
                    String generateID;
            if(bl.getList().isEmpty()) generateID = "B001"; 
            else{
                    Borrow borrow = bl.getList().getLast();
                    int lastIDNumber = Integer.parseInt(borrow.getbID().substring(1));
                    int newIDNumber = lastIDNumber + 1;
                    generateID = "B" + String.format("%03d", newIDNumber);
            }
            System.out.println(generateID);
            bl.addNewBorrow(new Borrow(generateID, re.getAssetID(),re.getEmployID() , re.getQuantity(), re.getRequestDateTime()));
            rl.saveFile("request.txt");
                    bl.saveFile("borrow.txt");
                    System.out.println("Appoved it!");
    }

    @Override
    public void showListOfBorrowAsset() {
           utils.displayAll(bl.getList());
    }

    

   
    
}
