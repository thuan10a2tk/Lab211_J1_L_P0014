
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BorrowList {
    List<Borrow> list;
    AssetList al = new AssetList();
    EmployeeList el = new EmployeeList();
    public BorrowList() {
        list = new ArrayList<>();
        readFile("borrow.txt");
    }

    public List<Borrow> getList() {
        return list;
    }

    private void readFile(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(fname))){
            String str;
            while((str = br.readLine()) != null){
                String [] part = str.split(",");
                
                    try {
                     if(part.length==5 &&  part[0].matches("^B\\d{3}")){
                    if(!part[1].matches("^A\\d{3}$") || !part[2].matches("^E\\d{6}$") || 
                            al.searchByLambda(p->p.getAssetID().equals(part[1])).isEmpty() ||
                            el.searchByLambda(p->p.getEmployID().equals(part[2])).isEmpty()) throw new Exception("ID is not exist or Id is invalid");
                    list.add(new Borrow(part[0], part[1], part[2], Integer.parseInt(part[3]), part[4]));
                     }
                     } catch (Exception  e) {
                        System.out.println(e.getMessage());
                    }
                
                   
            }
        } catch (Exception e) {System.out.println(e.getMessage());
        }
    }
     public void addNewBorrow(Borrow e){
        list.add(e);
        writeFile(e, "borrow.txt");
    }

    private void writeFile(Borrow e, String fname) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fname,true))){
            bw.write(e.toString());
            bw.newLine();
        } catch (IOException ex) {
        }
    }
    public boolean delete(String code){
        return list.removeIf(p->p.getbID().equals(code));
    }
    public void saveFile(String fname){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fname))){
            for (Borrow borrow : list) {
                bw.write(borrow.toString());
                bw.newLine();
                
            }
        } catch (Exception e) {
        }
    }
    public Borrow searchByID(String bID) {
        return list.stream().filter(p -> p.getbID().equals(bID)).findFirst().orElse(null);
    }
}
