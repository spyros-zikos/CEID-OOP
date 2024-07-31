abstract class Entity
{
    private String name;
    private String description;
    private int id;
    private int category;    // 0 for material AND 1 for service
    
    abstract String getDetails();

    public Entity() {}
    
    public Entity(String name, String description, int id, int category)
    {
        this.name = name;
        this.description = description;
        this.id = id;
        this.category = category;
    }
    
    public int getCategory() {return category;}
    public void setCategory(int category) {this.category = category;}
    
    private String getEntityInfo()       
    {
        return "Name: " + name + "\nDescription: " + description + "\nID: " + id;
    }
    
    public String toString()
    {
        return getEntityInfo() + "\n" + getDetails();
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
}
