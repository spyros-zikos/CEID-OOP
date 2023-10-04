import java.util.*;
import java.io.IOException;


public class Input
{
    private ArrayList<String> list = new ArrayList<String>();
    public static double pinf = Double.POSITIVE_INFINITY;
    
    public Input(String str)
    {
        list = new ArrayList(Arrays.asList(str.split(" ")));
    }
    
    public Input(ArrayList<String> list)
    {
        this.list = list;
    }
    
    public Input(String str, String splitter)
    {
        list = new ArrayList(Arrays.asList(str.split(splitter)));
    }
    
    public static void clearConsole()   
    {   
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
        } catch (Exception e) {}
    }
    
    public static int intInRange(String msg, double min, double max)
    {
        while (true) {
            try{
                System.out.println(msg);
                
                String ui = (new Scanner(System.in)).nextLine();
                Input.clearConsole();
                
                int selection = Integer.parseInt(ui);
                if (selection < min || selection > max) 
                    throw new NotAcceptedValueException();
                
                return selection;
                
            } catch (NotAcceptedValueException e) {
                    e.notifyMe();
                    
            } catch (NumberFormatException e){
                try{
                    throw new NotAcceptedValueException();
                } catch (NotAcceptedValueException v) {
                    v.notifyMe();
                }
                
            } catch (NoSuchElementException n){
                try{
                    throw new NotAcceptedValueException();
                } catch (NotAcceptedValueException v) {
                    v.notifyMe();
                }
            }
            
        }
    }
    
    public static String strInput(String msg)
    {
        while (true) {
            try{
                System.out.println(msg);
                String ui = (new Scanner(System.in)).nextLine();
                Input.clearConsole();
                
                return ui;
            } catch (NoSuchElementException a){
                try{
                    throw new NotAcceptedValueException();
                } catch (NotAcceptedValueException d) {
                    d.notifyMe();
                }
            }
        }
    }
    
    public String checkStrInList(String msg)
    {
        while (true) {
            try{
                System.out.println(msg);
                
                String ui = (new Scanner(System.in)).nextLine();
                
                Input.clearConsole();
                
                if (list.contains(ui) == false) 
                    throw new NotAcceptedValueException();
                    
                return ui;
                
            } catch (NotAcceptedValueException e) {
                    e.notifyMe();
                    
            } catch (NumberFormatException e){
                try{
                    throw new NotAcceptedValueException();
                } catch (NotAcceptedValueException v) {
                    v.notifyMe();
                }
                
            } catch (NoSuchElementException n){
                try{
                    throw new NotAcceptedValueException();
                } catch (NotAcceptedValueException v) {
                    v.notifyMe();
                }
            }
            
        }
    }
    
    public int checkIntInList(String msg)
    {
        while (true) {
            try{
                System.out.println(msg);
                
                String ui = (new Scanner(System.in)).nextLine();
                Input.clearConsole();
                
                if (list.contains(ui) == false) 
                    throw new NotAcceptedValueException();
                    
                int selection = Integer.parseInt(ui);
                
                return selection;
                
            } catch (NotAcceptedValueException e) {
                e.notifyMe();
                
            } catch (NumberFormatException e){
                try{
                    throw new NotAcceptedValueException();
                } catch (NotAcceptedValueException v) {
                    v.notifyMe();
                }
                
            } catch (NoSuchElementException n){
                try{
                    throw new NotAcceptedValueException();
                } catch (NotAcceptedValueException v) {
                    v.notifyMe();
                }
            }
            
        }
    }
    
    public static String checkPhone(String msg)
    {
        while (true) {
            try{
                System.out.println(msg);
                
                String ui = (new Scanner(System.in)).nextLine();
                Input.clearConsole();
                
                if (ui.equals("exit")) 
                    return "exit";
                
                else if (ui.length() != 10) 
                    throw new NotAcceptedValueException();
                
                Long.parseLong(ui);
                
                return ui;
                
            } catch (NotAcceptedValueException e) {
                e.notifyMe();
                
            } catch (NumberFormatException e){
                try{
                    throw new NotAcceptedValueException();
                } catch (NotAcceptedValueException r) {
                    r.notifyMe();
                }
                
            } catch (NoSuchElementException a){
                try{
                    throw new NotAcceptedValueException();
                } catch (NotAcceptedValueException d) {
                    d.notifyMe();
                }
            }
        }
    }
}
