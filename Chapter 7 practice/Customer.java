import java.util.ArrayList;

public class Customer
{
   
    public String name = "";
    public int cost;
    
    public Customer(String name, int cost)
    {
        this.name = name;
        this.cost = cost;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    public String getName()
    {
        return name;
    }

}
