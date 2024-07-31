import java.util.*;

public class RequestDonation
{
    private Entity entity;
    private double quantity = 0;
    
    public RequestDonation(Entity entity, double quantity)
    {
        this.entity = entity;
        this.quantity = quantity;
    }
    
    public Entity getEntity()
    {
        return entity;
    }
    
    public double getQuantity()
    {
        return quantity;
    }
    
    public void setQuantity(double quantity)
    {
        this.quantity = quantity;
    }
}
