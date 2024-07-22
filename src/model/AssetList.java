
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AssetList {
    List<Asset> list;

    public AssetList() {
        list = new ArrayList<>();
        readFile("asset.txt");
    }

    public List<Asset> getList() {
        return list;
    }

    private void readFile(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(fname))){
            String str;
            while((str = br.readLine())!= null){
                String [] part = str.split(",");
                if(part.length == 6 && part[0].matches("^A\\d{3}$")){
                    try {
                        list.add(new Asset(part[0], part[1], part[2], Double.parseDouble(part[3]), 
                            Double.parseDouble(part[4]), Integer.parseInt(part[5])));
                    } catch (NumberFormatException e) {
                    }
                }
                    
            }
        } catch (IOException e) {
        }
    }
    
    public void addNewAsset(Asset e){
        list.add(e);
        writeFile(e, "asset.txt");
    }

    private void writeFile(Asset a, String fname) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fname,true))){
            bw.write(a.toString());
            bw.newLine();
        } catch (IOException e) {
        }
    }
    public List<Asset> searchByLambda(Predicate<Asset> p){
        return list.stream().filter(p).collect(Collectors.toList());
    }

    public Asset searchByID(String assetID) {
        return list.stream().filter(p -> p.getAssetID().equals(assetID)).findFirst().orElse(null);
    }
    public void saveFile(String fname){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fname))){
            for (Asset asset : list) {
                bw.write(asset.toString());
                bw.newLine();
            }
        } catch (Exception e) {
        }
    }
}
