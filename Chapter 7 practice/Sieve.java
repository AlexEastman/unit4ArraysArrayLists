import java.util.Scanner;

public class Sieve
{
   public static int[] values;
   public static int currentSize;
   public static int currentSizeP;
   public static void main(String[] args)
   {
       Scanner in = new Scanner(System.in);
       System.out.println("Find primes below: ");   
       
       int n = in.nextInt();//reads input and such
       values = new int[n-2]; // enough space for an array of 2 to n
       int[] primes = new int[n-2]; 
       currentSize = n-2;
       currentSizeP = 0;
       for(int i = 0; i<values.length;i++)
       {
           values[i] = i+2;
       }
       while(currentSize>0)
       {
           int p = values[0];
           primes[currentSizeP] = p;
           currentSizeP++;
           for(int k=p*p;p<=n;k+=p)
           {
               int index = findIndex(k);
               if(index!=-1)
               {
                   currentSize--;
                   values[index]= values[currentSize-1];
                   
               }
           }
       }
       String str = "[ ";
       for(int i = 0; i<values.length; i++)
       {
           if (i>0)
           {
               str += ", ";
           }
           str += values[i];
       }
       str += " ]";
       System.out.println(str);
   }
   public static int findIndex(int k)//finds index returns -1 if not found
   {       
       for(int index = 0; index<currentSize;index++)
       {
           if(values[index] == k)
           {
               return index;
              
           }
           
       }
       return -1;      
       
   }
   
}
