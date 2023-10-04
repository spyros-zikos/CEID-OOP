public class Offers extends RequestDonationList
{
    private Donator donor;
    
    public Offers(Donator donor)
    {
        this.donor = donor;
    }
    
    public void commit()
    {
        for(RequestDonation rdlist: getRdEntities())
        {
            try
            {
                Organization.getOrg().getCurrentDonations().add(rdlist);
            }
            catch(RequestDonationException e)
            {
                try {
                    throw new RequestDonationException("This entity is" +
                              " no longer supported by the organization (" 
                                   + rdlist.getEntity().getName() + ").");             
                } catch (RequestDonationException rde) {
                        rde.notifyMe();
                }
            } 
        }
        this.reset();
    }
}