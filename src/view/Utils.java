package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Utils {
    private final String ID_VALID = "^A\\d{3}$";
    private final String PHONE_VALID = "^\\d{10}\\d*$";
    private final String EMAIL_VALID
            = "^[A-Za-z0-9.+-_%]+@[A-Za-z.-]+\\.[A-Za-z]{2,4}$";
    private static Scanner sc;
    public Utils() {
       sc =  new Scanner(System.in);
    }
    public String checkString(){
        while(true){
            String str = sc.nextLine().trim();
            if(!str.isEmpty())
                return str;
        }
    }
    public String getId(String s){
        while(true){
            System.out.println(s);
            String res = checkString();
            if(res.matches(ID_VALID))
                return res;
            else{
                System.out.println("Please enter the correct ID.");
            }
        }
    }
    public String getName(String s){
        String res="";
        while(true){
            System.out.println(s);
            res = sc.nextLine();
            if(!res.isEmpty()) break;
        }     
        return res;
    }
    public double getDouble(String s){
        while(true){
            try {
                System.out.println(s);
                double res = Double.parseDouble(sc.nextLine());
                return res;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static int getInteger(String s){
        while(true){
            try {
                System.out.println(s);
                int res = Integer.parseInt(sc.nextLine());
                return res;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public int getInt(String s){
        while(true){
            try {
                System.out.println(s);
                int res = Integer.parseInt(sc.nextLine());
                return res;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public String getString(String s){
        System.out.println(s);
        return sc.nextLine();
    }
    public boolean getBoolean(String s){
        while (true) {
            System.out.println(s);
            String res = checkString();
            if(res.equalsIgnoreCase("Yes")) return true;
            else if (res.equalsIgnoreCase("no")) return false;
            else  System.out.println("Must be enter yes or no: ");
        }
    }
    public String getPhone(String s){
        while(true){
            System.out.println(s);
            String res = checkString();
            if(res.matches(PHONE_VALID))
                return res;
            else {
                System.err.println("Phone is number with minimum 10 characters");
                System.out.print("Enter again: ");
            }
        }
    }
    public String getEmail(String s){
        while(true){
            System.out.println(s);
            String res = checkString();
            if(res.matches(EMAIL_VALID))
                return res;
            else {
                System.err.println("Email with format <account name>@<domain>");
                System.out.print("Enter again: ");
            }
        }
    }
    public Date getDateTime(String s) throws ParseException{
        while(true){
            System.out.println(s);
            String str = checkString();
            try {
                SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy");
                return time.parse(str);
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String checkID(String s){
        if(s.matches(ID_VALID))
            return s;
        else return null;
    }
    public <O> void displayAll(List<O> L) {
            System.out.println("---------------------");
            for (O obj : L) {
                System.out.println(obj);
            }
            System.out.println("---------------------");
            System.out.println("Total : " + L.size());
        
    }
    public static String toStringDate(LocalDate time){
        return time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public static LocalDate toLocalDate(String time){
        return LocalDate.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}