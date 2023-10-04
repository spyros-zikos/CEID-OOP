public class Material extends Entity
{
    private final double level1;
    private final double level2;
    private final double level3;
 
    public Material(String name, String description,int id, int category,
                    double level1, double level2, double level3)
    {
        super(name, description, id, category);
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
    }
    
    public double getLevel1()
    {
        return level1;
    }
    
    public double getLevel2()
    {
        return level2;
    }
    
    public double getLevel3()
    {
        return level3;
    }
    
    public String getDetails()
    {
        return "Type: Material\nLevel 1: " + level1 + "\nLevel 2: " + 
                level2 + "\nLevel 3: " + level3;
    }
}