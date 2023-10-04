public class InsufficientQuantityException extends RequestDonationException
{
    private String msg = "You ask too much!";
    
    public InsufficientQuantityException() 
    {
        super();
    }
    
    public InsufficientQuantityException(String msg)
    {
        super(msg);
    }
}
