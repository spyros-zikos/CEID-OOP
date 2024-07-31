import java.util.*;

public class RequestDonationList
{
    private ArrayList<RequestDonation> rdEntities = new ArrayList<RequestDonation>();

    public RequestDonation get(int id) throws RequestDonationException 
    //Throws RequestDonationNotFoundException 
    //if "rd.entity" is not found in "rdEntities".
    {
        for(RequestDonation rd: rdEntities)
        {
            if(rd.getEntity().getId() == id) return rd;
        }
        throw new RequestDonationNotFoundException();
    }
    
    
    public void add(RequestDonation rd) throws RequestDonationException 
    //Throws RequestDonationNotFoundException 
    //if "rd.entity" is not found in "Organization.entityList".
    {   
        int flag = 0;
        for(Entity entity: Organization.getOrg().getEntityList())
        {
            if(rd.getEntity().getId() == entity.getId()) flag = 1;
        }
        if(flag == 0) throw new RequestDonationNotFoundException("" +
                    "This entity is not supported by our organization.");
                              
        for(RequestDonation rdon: rdEntities)
        {
            if(rdon.getEntity().getId() == (rd.getEntity()).getId())
            {
                rdon.setQuantity(rdon.getQuantity() + rd.getQuantity());
                return;
            }
        }
        rdEntities.add(rd);                          
        
    }
    
    public void remove(int id) throws RequestDonationException 
    //See get() for exception handling.
    {
        rdEntities.remove(this.get(id));
    }
    
    public void modify(int id, double quantity) throws RequestDonationException 
    //See get() for exception handling.
    {
        get(id).setQuantity(quantity);
    }
    
    public String monitor()
    {
        String rds = "";
        int no = 1;
        
        for(RequestDonation rdlist: rdEntities)
        {
            rds += "-" + no + "-\n" + "Name: " + rdlist.getEntity().getName() +
                                   "   ID: " + rdlist.getEntity().getId() +
                                   "   Quantity: " + (long)rdlist.getQuantity() + "\n";
            no++;
        }
        return rds + "\n";
    }
    
    public void reset()
    {
        rdEntities.clear();
    }
    
    public ArrayList<RequestDonation> getRdEntities()
    {
        return rdEntities;
    }
}
