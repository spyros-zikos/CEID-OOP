import java.util.*;

public class Organization
{
    private static Organization org;
    private String name;
    private Admin admin;
    private ArrayList<Entity> entityList = new ArrayList<Entity>();
    private ArrayList<Donator> donatorList = new ArrayList<Donator>();
    private ArrayList<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
    private RequestDonationList currentDonations;

    
    public Organization(String name, Admin admin)
    {
        this.name = name;
        this.admin = admin;
        this.org=this;
        currentDonations = new RequestDonationList();
    }
    
    public ArrayList<String> getStrMaterialList()
    {
        ArrayList<String> names = new ArrayList<String>();
        
        for (Entity entity: entityList)
        {
            if(entity.getCategory() == 0)
                names.add(entity.getName());
        }
        return names;
    }
    
    public ArrayList<String> getStrServiceList()
    {
        ArrayList<String> names = new ArrayList<String>();
        
        for (Entity entity: entityList)
        {
            if(entity.getCategory() == 1)
                names.add(entity.getName());
        }
        return names;
    }
    
    public static Organization getOrg()
    {
        return org;
    }
    
    public ArrayList<Entity> getEntityList()
    {
        return entityList;
    }
    
    public ArrayList<Donator> getDonatorList()
    {
        return donatorList;
    }
    
    public ArrayList<Beneficiary> getBeneficiaryList()
    {
        return beneficiaryList;
    }
    
    public Entity getEntityFromName(String name, int category)
    {
        for (Entity entity: entityList)
        {
            if (entity.getName().equals(name) && (entity.getCategory() == category || category == -1)) 
                return entity;
        }
        
        return new Material("error", "bug", -1, 0, 1, 2, 3);
    }
    
    public RequestDonationList getCurrentDonations()
    {
        return currentDonations;
    }
    
    public void addOffer(RequestDonation rd) throws RequestDonationException
    {
        try
        {
            currentDonations.add(rd);
        }
        catch (RequestDonationNotFoundException rdnfe)
        {
            rdnfe.notifyMe();
        }
    }
    
    
    public String strMaterials()
    {
        String names = "";
        names += "Materials:\n";
        for (Entity entity: entityList)
        {
            if (entity.getCategory() == 0) 
            {
                names += "\t-" + entity.getName() + "\n";
            }
        }
        return names;
    }
    
    public String strServices()
    {
        String names = "";
        names += "Services:\n";
        for (Entity entity: entityList)
        {
            if (entity.getCategory() == 1) 
            {
                names += "\t-" + entity.getName() + "\n";
            }
        }
        return names;
    }
    
    public String getCategories()
    {
       return "Materials: (" + getNoOfMaterials() + ").\n" +
              "Services: (" + getNoOfServices() + ").\n";
    }
    
    
    public int getNoOfMaterials()
    {
        int no = 0;
        for (Entity entity: entityList)
        {
            if (entity.getCategory() == 0) no++;
        }
        return no;
    }
    
    public int getNoOfServices()
    {
        int no = 0;
        for (Entity entity: entityList)
        {
            if (entity.getCategory() == 1) no++;
        }
        return no;
    }
    
    
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    
    public void setAdmin(Admin admin) {this.admin = admin;}
    public Admin getAdmin() {return admin;}

    public void addEntity(Entity entity)
    {
        try{
            if(entityList.contains(entity))throw new AlreadyExistsException();
                entityList.add(entity);
        } catch (AlreadyExistsException e) {
            e.notifyMe();
        }
    }
    
    public void removeEntity(Entity entity)
    {
        entityList.remove(entity);
    }
    
    
    public int insertDonator(Donator possibleDonator)
    {
        try{
            for(Donator donator : donatorList)
            {
                if (donator.getPhone().equals(possibleDonator.getPhone())) 
                    throw new AlreadyExistsException();
            }
            donatorList.add(possibleDonator);
            return 2;
        } catch (AlreadyExistsException e) {
            e.notifyMe();
            return 0;
        }
    }
    
    public void removeDonator(Donator donator)
    {
        donatorList.remove(donator);
    }
    
    
    public int insertBeneficiary(Beneficiary possibleBeneficiary)
    {
        try{
            for(Beneficiary beneficiary : beneficiaryList)
            {
                if (beneficiary.getPhone().equals(possibleBeneficiary.getPhone())) 
                    throw new AlreadyExistsException();
            }
            beneficiaryList.add(possibleBeneficiary);
            return 2;
        } catch (AlreadyExistsException e) {
            e.notifyMe();
            return 0;
        }
    }
    
    public void removeBeneficiary(Beneficiary beneficiary)
    {
        beneficiaryList.remove(beneficiary);
    }
    
    public String listEntities()
    {
        String matstr = "-Materials-";
        String serstr = "\n-Services-";
        for (Entity entity: entityList)
        {
            if(entity.getCategory()==0) matstr += "\n" + entity.getName();
            else serstr += "\n" + entity.getName();
        }
        return matstr + serstr;
    }
    
    public String listDonators()
    {
        String out = "-Donator (Phone)-";
        int no = 1;
        for (Donator donor : donatorList)
        {
            out += "\n" + no + ". " + donor.getName() + " (" + donor.getPhone() + ")";
            no++;
        }
        return out;
    }
    
    public String listBeneficiaries()
    {
        String out = "-Beneficiary (Phone)-";
        int no = 1;
        for (Beneficiary benef : beneficiaryList)
        {
            out += "\n" + no + ". " + benef.getName() + " (" + benef.getPhone() + ")";
            no++;
        }
        return out;
    }
    
    public int checkAccount(String phone)
    {
        ArrayList<String> phoneList = new ArrayList<String>();
        
        for(Donator donator : donatorList)
            {phoneList.add(donator.getPhone());}
        
        for(Beneficiary beneficiary : beneficiaryList)
            {phoneList.add(beneficiary.getPhone());}
        
        phoneList.add(admin.getPhone());
        
        if (phoneList.contains(phone)) return 2;
        else return 1;
    }
    
    public String whatIsThisPhone(String phone)
    {
        for(Donator donator : donatorList)
            {
                if (phone.equals(donator.getPhone())) return "donator";
            }
            
        for(Beneficiary beneficiary : beneficiaryList)
            {
                if (phone.equals(beneficiary.getPhone())) return "beneficiary";
            }
            
        if (phone.equals(admin.getPhone())) return "admin";
        
        return "error";
    }
    
    
    public Donator getDonatorFromPhone(String phone)
    {
        for(Donator donator : donatorList)
        {
            if (phone.equals(donator.getPhone())) return donator;
        }
        return new Donator("error", "bug");
    }
    
    public Beneficiary getBeneficiaryFromPhone(String phone)
    {
        for(Beneficiary beneficiary : beneficiaryList)
        {
            if (phone.equals(beneficiary.getPhone())) return beneficiary;
        }
        return new Beneficiary("error", "bug");
    }
    
}
