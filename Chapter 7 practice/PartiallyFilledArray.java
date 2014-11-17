public class PartiallyFilledArray
{
    private int[] values;
    private int currentSize;
    
    public PartiallyFilledArray()
    {
        values = new int[10];
    }
    
    public String toString()
    {
        String str = "[ ";
        
        for(int i =  0; i<currentSize;i++)
        {
            if(i>0)
            {
                str +=", ";
            }
            str+=values[i];
        }
        str += " ]";
        return str;
    }
    
    public void fillInElementsRandomly( int numberOfElements, int maxValue)
    {
        for(int i =0; i<numberOfElements; i++)
        {
            values[i] = (int)(Math.random() * maxValue);
            currentSize++;
        }
    }
    
    public void removeElement(int index)
    {
        values[index] = values[currentSize - 1];
        currentSize--;
    }
    
    public void swapElements(int a, int b)
    {
        int c = values[a];
        values[a] = values[b];
        values[b] = c;
        
        
    }
}
