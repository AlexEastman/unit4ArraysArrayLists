
/**
 * The model for radar scan and accumulator
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class Radar
{
    
    // stores whether each cell triggered detection for the current scan of the radar
    private boolean[][] currentScan;
    private boolean[][] lastScan;
    
    private int velocityX;
    private int velocityY;
    
    // value of each cell is incremented for each scan in which that cell triggers detection 
    private int[][] accumulator;
    
    // location of the monster
    private int monsterLocationRow;
    private int monsterLocationCol;

    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;

    public float getNoiseFraction()
    {
        return (float) noiseFraction;
    }
    
    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols, int dx, int dy)
    {
        // initialize instance variables
        lastScan = new boolean[rows][cols]; // elements will be set to false
        currentScan = new boolean[rows][cols]; // elements will be set to false
        accumulator = new int[11][11]; 
        velocityX = dx;
        velocityY = dy;
        
        // randomly set the location of the monster (can be explicity set through the
        //  setMonsterLocation method
        monsterLocationRow = (int)(Math.random() * rows/2);
        monsterLocationCol = (int)(Math.random() * cols/2);
        if(dx<0)
        {
            monsterLocationCol+=50;
        }
        
        if(dy<0)
        {
            monsterLocationRow +=50;
        }
        
        noiseFraction = 0;
        numScans= 0;
    }
    
    
     /**
     * updates the accumulator of all the dx dy combinations
     *
     * @param no parameters
     * @return    returns void
     */

    public void updateAccumulator()
    {
        for(int r=0;r<currentScan.length;r++)
        {
            for(int c = 0; c<currentScan[0].length; c++)
            {
                if( currentScan[r][c] == true)
                {
                    //set search parameters
                    int upperX = c+5;
                    int upperY = r+5;
                    int lowerX = c-5;
                    int lowerY = r-5;
                    
                    //insures the upper bounds never exceed the dimensions of the scans
                    upperX = ((upperX-currentScan[0].length)-Math.abs(upperX-currentScan[0].length))/2 + currentScan[0].length; 
                    upperY = ((upperY-currentScan.length)-Math.abs(upperY-currentScan.length))/2 + currentScan.length;
                    //insures the lower bounds never exceed the dimensions of the scans
                    lowerX = (lowerX+ Math.abs(lowerX))/2;
                    lowerY = (lowerY+ Math.abs(lowerY))/2;
                    
                    for(int y=lowerY; y<upperY; y++)
                    {
                        for(int x = lowerX; x<upperX; x++)
                        {
                            if(lastScan[y][x] == true)
                            {
                                accumulator[r-y+5][c-x+5]++;//increments the found dx dy pair.
                            }
                            }
                    }
                    
                }
            }
        }
    }
    
    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     */
    public boolean scan()
    {
        updateLastScan();
        // zero the current scan grid
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                currentScan[row][col] = false;
            }
        }
        
        // detect the monster
        currentScan[monsterLocationRow][monsterLocationCol] = true;
        
        // inject noise into the grid
        injectNoise();
        
        // udpate the accumulator
        updateAccumulator();
        
        // keep track of the total number of scans
        numScans++;
        
        return moveMonster();
    }

    /**
    * moves the monster according to the known dx and dy values
    *
    * @param  no parameter
    * @return     boolean that determines stop condition in radar viewer while loop
    */
    private boolean moveMonster()
    {
        int y = monsterLocationRow+velocityY;
        int x = monsterLocationCol+velocityX;
        if(y<100 && y>0 && x<100 && x>0)
        {
            setMonsterLocation(y,x);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation(int row, int col)
    {
        // remember the row and col of the monster's location
        monsterLocationRow = row;
        monsterLocationCol = col;
        
        // update the radar grid to show that something was detected at the specified location
        currentScan[row][col] = true;
    }
    
     /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }
    
    /**
     * Returns true if the specified location in the radar grid triggered a detection.
     * 
     * @param   row     the row of the location to query for detection
     * @param   col     the column of the location to query for detection
     * @return true if the specified location in the radar grid triggered a detection
     */
    public boolean isDetected(int row, int col)
    {
        return currentScan[row][col];
    }
    
    /**
     * Returns the number of times that the specified location in the radar grid has triggered a
     *  detection since the constructor of the radar object.
     * 
     * @param   row     the row of the location to query for accumulated detections
     * @param   col     the column of the location to query for accumulated detections
     * @return the number of times that the specified location in the radar grid has
     *          triggered a detection since the constructor of the radar object
     */
    public int getAccumulatedDetection(int row, int col)
    {
        return accumulator[row][col];
    }
    
    /**
    * calculates and returns the dx and dy value
    *
    * @param  y   no parameters
    * @return  the int[] array dx is stored in index 0 dy is in index 1
    */
    public int[] getDxDy()
    {
        int[] arr = new int[2];
        
        int dx = 0;
        int dy = 0;
        int v = accumulator[0][0];
        for(int r = 0; r<11; r++)
        {
            for(int c = 1; c<11; c++)
            {
                if(accumulator[r][c]>v)
                {
                    v=accumulator[r][c];
                    dx = c;
                    dy = r;
                }
            }
        }
        arr[0] = dx-5;//offset from indexs to velocity is 5
        arr[1] = dy-5;
        return arr;
        
    }
    
    /**
     * Returns the number of rows in the radar grid
     * 
     * @return the number of rows in the radar grid
     */
    public int getNumRows()
    {
        return currentScan.length;
    }
    
    /**
     * Returns the number of columns in the radar grid
     * 
     * @return the number of columns in the radar grid
     */
    public int getNumCols()
    {
        return currentScan[0].length;
    }
    
    /**
     * Returns the number of scans that have been performed since the radar object was constructed
     * 
     * @return the number of scans that have been performed since the radar object was constructed
     */
    public int getNumScans()
    {
        return numScans;
    }
    
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                // each cell has the specified probablily of being a false positive
                if(Math.random() < noiseFraction)
                {
                    currentScan[row][col] = true;
                }
            }
        }
    }
    
    
    /**
    * this method copies all elements from the current scan to the old scan so they can be compared
    *
    * @param  no parameters
    * @return    void
    */
    private void updateLastScan()
    {
        for(int r = 0; r<currentScan.length;r++)
        {
            for( int c = 0; c<currentScan[0].length; c++)
            {
                lastScan[r][c] = currentScan[r][c];
            }
        }
    }
}


