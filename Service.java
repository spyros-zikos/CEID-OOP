public class Service extends Entity
{
    public Service() {}
    
    public Service(String name, String description, int id, int category)
    {
        super(name, description, id, category);
    }
    
    public String getDetails()
    {
        return "Type: Service";
    }
}