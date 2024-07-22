
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EmployeeList {
    List<Employee> list;

    public EmployeeList() {
        list = new ArrayList<>();
        readFile("employee.txt");
    }

    public List<Employee> getList() {
        return list;
    }

    private void readFile(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(fname))){
            String str;
            while((str = br.readLine())!=null){
                String[] part = str.split(",");
                if(part.length == 6 && part[0].matches("^E\\d{6}$")){
                    try {
                          list.add(new Employee(part[0], part[1], part[2], part[3], part[4], part[5]));
                    } catch (Exception e) { System.out.println(e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Employee> searchByLambda(Predicate<Employee> p){
        return list.stream().filter(p).collect(Collectors.toList());
    }
    public void addNewEployee(Employee e){
        list.add(e);
        writeFile(e, "employee.txt");
    }

    private void writeFile(Employee e, String fname) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fname,true))){
            bw.write(e.toString());
            bw.newLine();
        } catch (IOException ex) {
        }
    }
    public boolean delete(String code){
        return list.removeIf(p->p.getEmployID().equals(code));
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes());

            // bytes to hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public Employee searchByID(String employID) {
        return list.stream().filter(p -> p.getEmployID().equals(employID)).findFirst().orElse(null);
    }
    public Employee login(String employID, String password){
        Employee employee = searchByID(employID);
        if(employee == null) {
            System.out.println("Incorrect id or password");  return null;
        }
        try {
            
            if(hashPassword(password).equals(employee.getPassword())){
                System.out.println("Successfully");
            } else {
                System.out.println("Incorrect id or password"); return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }
    
}
