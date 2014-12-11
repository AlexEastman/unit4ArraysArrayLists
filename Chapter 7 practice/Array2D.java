
public class Array2D
{
    /** description of instance variable x (add comment for each instance variable) */
    private int[][] values= 
        {
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {10,11,12}
        };

    
    public static void main(String[] args)
    {
        Array2D values = new Array2D();
        System.out.println(values.toString());
        System.out.println(values.extractRow(1));
        System.out.println
        
    }
    
    public String extractRow(int r)
    {
        String str = "";
        
        for(int c=0; c<values[r].length;c++)
        {
            str += values[r][c]+"\t";
        }
        
        return str;
    }
    
    public String ExtractCol(int c)
    {
        String str = "";
        for(int r = 0; r<values.length;r++)
        {
            str+= values[r][c]+"\n";
        }
        return str;
    }
    
    public String toString()
    {
        String str = "";
        for(int r=0; r<values.length;r++)
        {
            for(int c=0; c<values[r].length;c++)
            {
                str+=values[r][c]+"\t";
                
            }
            str+= "\n";
        }
       return str;
    }

}
