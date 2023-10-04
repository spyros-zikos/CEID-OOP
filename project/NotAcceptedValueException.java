public class NotAcceptedValueException extends Exception
{
    private String msg = "Not accepted value";
    
    
    public NotAcceptedValueException()
    {
        
    }
    
    public NotAcceptedValueException(String msg)
    {
        this.msg = msg;
    }
    
    public void notifyMe()
    {
        Menu.notification(msg, Menu.seconds);
    }
}
