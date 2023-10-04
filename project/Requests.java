import java.util.*;

public class Requests extends RequestDonationList
{
    private Beneficiary benef;
    
    public Requests(Beneficiary benef)
    {
        this.benef = benef;
    }
    
    public void add(RequestDonation rd) throws RequestDonationException 
    //InsufficientQuantityException, InvalidRequestException
    //and RequestDonationNotFoundException.
    {
        boolean alreadyInRequests = true;
        try {
            get(rd.getEntity().getId());
        } catch(RequestDonationException rde) {
            alreadyInRequests = false;
        }
        
        try {
            if (Organization.getOrg().getCurrentDonations().get(rd.getEntity().getId()).getQuantity() 
                < rd.getQuantity() + ( alreadyInRequests?
                                       get(rd.getEntity().getId()).getQuantity():
                                       0 ) )
            {
                throw new InsufficientQuantityException("Not enough stock."); 
                //Is thrown when the organization doesn't have enough stock.
            }
        } catch (RequestDonationNotFoundException rde) {
            throw new InsufficientQuantityException("Not enough stock."); 
            //Is thrown when the organization doesn't have enough stock.
        }

        validRequestDonation(rd); //Throws InvalidRequestException.
        super.add(rd); //Throws RequestDonationNotFoundException 
                       //if "rd.entity" is not found in "Organization.entityList".
    }
    
    public void modify(int id, double quantity) throws RequestDonationException 
    //InsufficientQuantityException, InvalidRequestException
    //and RequestDonationNotFoundException.
    {
        try{
            if(Organization.getOrg().getCurrentDonations().get(id).getQuantity() < quantity)
            {
                throw new InsufficientQuantityException("Not enough stock."); 
                //Is thrown when the organization doesn't have enough stock.
            }
        } catch (RequestDonationNotFoundException rde) {
            throw new InsufficientQuantityException("Not enough stock."); 
            //Is thrown when the organization doesn't have enough stock.
        }

        validRequestDonation(id, quantity); 
        //Throws InvalidRequestException (See validRequestDonation()).
        super.modify(id, quantity); 
        //Throws RequestDonationNotFoundException if no RequestDonation
        //with the id is found in the instance which runs the method.
    }
    
    public void validRequestDonation(RequestDonation rd) throws RequestDonationException 
    //Throws InvalidRequestException when the beneficiary
    //should not be allowed the request due to quantity per beneficiary.
    {
        boolean alreadyInRequests = true;
        try {
            get(rd.getEntity().getId());
        } catch(RequestDonationException rde) {
            alreadyInRequests = false;
        }
        
        boolean alreadyInReceived = true;
        try {
            benef.getReceivedList().get(rd.getEntity().getId()).getQuantity();
        } catch(RequestDonationException rde) {
            alreadyInReceived = false;
        }
        
        double alreadyRequestedQuantity = ( alreadyInRequests?
                                   get(rd.getEntity().getId()).getQuantity():
                                   0 );
        double alreadyReceivedQuantity = ( alreadyInReceived?
                        benef.getReceivedList().get(rd.getEntity().getId()).getQuantity():
                                   0 );                         
        
        if(rd.getEntity().getCategory() == 0)
        {
                if(benef.getNoPersons() < 2)
                {
                    if(((Material)rd.getEntity()).getLevel1() 
                        < rd.getQuantity() + alreadyRequestedQuantity +
                        alreadyReceivedQuantity) 
                        throw new InvalidRequestException("You don't deserve it!");
                }
                else if(benef.getNoPersons() < 5)
                {
                    if(((Material)rd.getEntity()).getLevel2() 
                        < rd.getQuantity() + alreadyRequestedQuantity +
                        alreadyReceivedQuantity) 
                        throw new InvalidRequestException("You don't deserve it!");
                }
                else
                {
                    if(((Material)rd.getEntity()).getLevel3() 
                        < rd.getQuantity() + alreadyRequestedQuantity +
                        alreadyReceivedQuantity) 
                        throw new InvalidRequestException("You don't deserve it!");
                }
        }
    }
    
    public void validRequestDonation(int id, double quantity) throws RequestDonationException 
    //Same exception as above plus RequestDonationNotFoundException
    //If no RequestDonation with the given id exists in the list.
    {
        boolean alreadyInReceived = true;
        try {
            benef.getReceivedList().get(id).getQuantity();
        } catch(RequestDonationException rde) {
            alreadyInReceived = false;
        }
        double alreadyReceivedQuantity = ( alreadyInReceived?
                                   benef.getReceivedList().get(id).getQuantity():
                                   0 );  
                                   
        if(get(id).getEntity().getCategory() == 0)
        {
            
                if(benef.getNoPersons() < 2)
                {
                    if(((Material)get(id).getEntity()).getLevel1() < quantity +
                    alreadyReceivedQuantity) throw new InvalidRequestException("" +
                    "You don't deserve it!");
                }
                else if(benef.getNoPersons() < 5)
                {
                    if(((Material)get(id).getEntity()).getLevel2() < quantity +
                
                    alreadyReceivedQuantity) throw new InvalidRequestException("" +
                    "You don't deserve it!");
                }
                else
                {
                    if(((Material)get(id).getEntity()).getLevel3() < quantity +
                    alreadyReceivedQuantity) throw new InvalidRequestException("" +
                    "You don't deserve it!");
                }
        }
    }
    
    public void commit()
    {
        ArrayList<RequestDonation> rdentities = new ArrayList<RequestDonation>();
        
        for(int i = 0; i < getRdEntities().size(); i++) 
            rdentities.add(getRdEntities().get(i));
        
        double subtraction;
        
        for(RequestDonation rd: rdentities)
        {
            try
            {
                try{
                    subtraction = 
                    Organization.getOrg().getCurrentDonations().get(rd.getEntity().getId()).getQuantity() 
                    - rd.getQuantity();
                } catch(RequestDonationNotFoundException e) {
                    throw new InsufficientQuantityException();
                }
                
                if(subtraction < 0) throw new InsufficientQuantityException();
                
                validRequestDonation(rd.getEntity().getId(), rd.getQuantity());
                
                Organization.getOrg().getCurrentDonations().modify(rd.getEntity().getId(), subtraction);
                
                benef.addReceived(rd);
                
                remove(rd.getEntity().getId());
            }
            catch(InsufficientQuantityException e)
            {
                try {
                    throw new RequestDonationException("The request concerning " + 
                                   rd.getEntity().getName() +
                                   " of quantity " + rd.getQuantity() +
                                   " cannot be at the moment handled because of insufficient stock.");
                } catch (RequestDonationException rde) {
                    rde.notifyMe();
                }
                
            }
            catch(InvalidRequestException e)
            {
                try {
                    throw new RequestDonationException("You aren't presently eligible for " + 
                                        rd.getQuantity() + " of " + rd.getEntity().getName());
                } catch (RequestDonationException rde) {
                    rde.notifyMe();
                }
            }
            catch(RequestDonationNotFoundException e)
            {
                try {
                    throw new RequestDonationException("The request concerning " + 
                                                        rd.getEntity().getName() +
                                     " cannot be at the moment handled because " +
                                     "it is no longer provided by the organization.");
                } catch (RequestDonationException rde) {
                    rde.notifyMe();
                }
            }
            catch(RequestDonationException e)
            {
                try {
                    throw new RequestDonationException("An unknown error has occured (" + 
                               rd.getEntity().getName() + " & " +rd.getQuantity() + ").");
                } catch (RequestDonationException rde) {
                    rde.notifyMe();
                }
            }
        }
    }
}