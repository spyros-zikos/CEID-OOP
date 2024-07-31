public class Donator extends User
{
    private Offers offersList = new Offers(this);
    
    public Donator(String name, String phone)
    {
        super(name, phone);
    }
     
    public void addOffer(RequestDonation rd) throws RequestDonationException 
    //Throws RequestDonationNotFoundException 
    //if "rd.entity" is not found in "Organization.entityList".
    {
        offersList.add(rd);
    }
    
    public void removeOffer(int id) throws RequestDonationException 
    //Throws RequestDonationNotFoundException 
    //if no RequestDonation with the id is found in "offersList".
    {
        offersList.remove(id);
    }
    
    public void modifyOffer(int id, double quantity) throws RequestDonationException 
    //Throws RequestDonationNotFoundException 
    //if no RequestDonation with the id is found in "offersList".
    {
        offersList.modify(id, quantity);
    }
    
    public String monitorOffers()
    {
        return "CURRENT OFFERS\n" + offersList.monitor();
    }
    
    public void commitOffers()
    {
        offersList.commit();
    }
    
    public void resetOffers()
    {
        offersList.reset();
    }
    
    public Offers getOffersList()
    {
        return offersList;
    }
}
