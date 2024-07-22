package view;

import java.util.ArrayList;

public abstract class Menu<T> {
    protected String title;
    protected ArrayList<T> mChon;
    protected boolean running; 
    
    public Menu(){}
    
    public Menu(String td, String[] mc){
        title=td;
        mChon= new ArrayList<>();
        for(String s:mc) mChon.add((T) s);
    }
//-------------------------------------------
    public void display(){
        System.out.println(title);
        System.out.println("--------------------------------");
        for(int i=0; i<mChon.size();i++)
            System.out.println((i+1)+"."+mChon.get(i));
        System.out.println("--------------------------------");
    }
//-------------------------------------------
    public int getSelected(){
        display();
        return Utils.getInteger("Enter selection..");
    }
//-------------------------------------------
    public abstract void execute(int n);
//-------------------------------------------
    public void run(){
        running = true;
        while(running){
            int n=getSelected();
            if(n<=mChon.size())execute(n);
            else break;
        }
    }
//-------------------------------------------    
}
