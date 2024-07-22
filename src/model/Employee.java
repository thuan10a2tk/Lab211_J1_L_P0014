
package model;

import java.time.LocalDate;
import view.Utils;

public class Employee {
    private String employID,name;
    private LocalDate birthDate;
    private String role;
    private boolean sex;
    private String password;

    public Employee() {
    }

    public Employee(String employID, String name, String birthDate, String role, String sex, String password) {
        setEmployID(employID);
        this.name = name;
        setBirthDate(birthDate);
        this.role = role;
        setSex(sex);
        this.password = password;
    }

    public String getEmployID() {
        return employID;
    }

    public final void setEmployID(String employID) {
        if(employID.matches("^E\\d{6}$")) this.employID = employID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public final void setBirthDate(String birthDate) {
        this.birthDate = Utils.toLocalDate(birthDate);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isSex() {
        return sex;
    }

    public final void setSex(String sex) {
        if(sex.equalsIgnoreCase("male")) this.sex = true;
        else if(sex.equalsIgnoreCase("female")) this.sex = false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return employID + "," + name + "," + Utils.toStringDate(birthDate) +  "," + role + ","+ sex +"," +password;
    }
    
    
}
