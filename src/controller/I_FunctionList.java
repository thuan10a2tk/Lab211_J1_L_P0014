
package controller;

import model.Employee;

public interface I_FunctionList {
    Employee login();
    
    void searchAssetByName();
    
    void createNewAsset();
    
    void updateAssetInformation();
    
    void appoveTheRequestOfEmployee();
    
    void showListOfBorrowAsset();
}
