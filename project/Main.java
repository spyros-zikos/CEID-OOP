import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Admin theAdmin = new Admin("Tim", "1234567890");
        Organization org = new Organization("GoodOrg", theAdmin);
        
        org.insertDonator(new Donator("Park", "3333333333"));
        org.insertDonator(new Donator("Bark", "1231231231"));
        org.insertBeneficiary(new Beneficiary("Mark", "1111111111"));
        org.insertBeneficiary(new Beneficiary("Clark", "2222222222"));
        org.insertBeneficiary(new Beneficiary("Dark", "3213213211"));
        
        org.addEntity(new Material("Rice", "One package containing 1kg of rice.", 16, 0, 5, 20, 50));
        org.addEntity(new Material("Milk", "A single 1L bottle of milk.", 14, 0, 3, 10, 20));
        org.addEntity(new Material("Sugar", "One pack containing 1kg of sugar.", 12, 0, 2, 4, 10));
        
        org.addEntity(new Service("Medical Support", "One 1-hour visit to healthcare facilities.", 10, 1));
        org.addEntity(new Service("Nursery Support", "Provision of nursing services for 1 hour per request.", 8, 1));
        org.addEntity(new Service("Babysitting", "One hour babysitting per request.", 6, 1));
        
        
        Menu menu = new Menu();
        menu.startMenu();
    }
}
