public class InvalidRequestException extends RequestDonationException
{
    private String msg = "You dont deserve it!";
    
    public InvalidRequestException() 
    {
        super();
    }
    
    public InvalidRequestException(String msg)
    {
        super(msg);
    }
}
