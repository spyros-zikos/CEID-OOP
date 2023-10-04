public class AlreadyExistsException extends Exception
{
    private String msg = "Already exists";
    
    public AlreadyExistsException()
    {
        
    }
    
    public AlreadyExistsException(String msg)
    {
        this.msg = msg;
    }
    
    public void notifyMe()
    {
        Menu.notification(msg, Menu.seconds);
    }
}
