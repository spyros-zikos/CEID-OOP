public class RequestDonationException extends Exception
{
    private String msg = "We were unable to register your request.\nPlease try again.";
    
    public RequestDonationException()
    {
        
    }
    
    public RequestDonationException(String msg)
    {
        this.msg = msg;
    }
    
    public void notifyMe()
    {
        Menu.notification(msg, Menu.seconds);
    }
    
}
