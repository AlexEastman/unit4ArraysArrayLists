

/**
 * stores a vector.
 * 
 * @author Alex Eastman
 * @version 1.0
 */
public class Vector
{
   
    private int dx;
    private int dy;
    private int numFound;
    
    public Vector(int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx()
    {
        return dx;
    }
    
    public int getDy()
    {
        return dy;
    }
    
    public int getNumFound()
    {
        return numFound;
    }
    
    public void incrementNumFound()
    {
        numFound++;
    }

}
