

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RadarViewerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RadarViewerTest
{
    /**
     * Default constructor for test class RadarViewerTest
     */
    public RadarViewerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        
    }
    
    @Test
    public void testZeroZero()throws InterruptedException
    {
        RadarViewer radarViewer = new RadarViewer(0,0);
        int[] expected = {0,0};
        int[] actual = radarViewer.getdxdy();
        assertArrayEquals(expected,actual);
        
    }
    
    @Test
    public void testMinus1Minus1()throws InterruptedException
    {
        RadarViewer radarViewer = new RadarViewer(-1,-1);
        int[] expected = {-1,-1};
        int[] actual = radarViewer.getdxdy();
        assertArrayEquals(expected,actual);
        
    }
    
    @Test
    public void testPlus1Plus1()throws InterruptedException
    {
        RadarViewer radarViewer = new RadarViewer(1,1);
        int[] expected = {1,1};
        int[] actual = radarViewer.getdxdy();
        assertArrayEquals(expected,actual);
        
    }


    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
