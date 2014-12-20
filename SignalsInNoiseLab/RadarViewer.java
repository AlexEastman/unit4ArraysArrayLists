import javax.swing.JFrame;
import java.util.Scanner;
/**
 * Class that contains the main method for the program and creates the frame containing the component.
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class RadarViewer
{
    Radar radar;
    
    /**
     * main method for the program which creates and configures the frame for the program
     *
     */
    public RadarViewer()throws InterruptedException
    {
        // create the radar, set the monster location, and perform the initial scan
        Scanner in = new Scanner(System.in);
        System.out.println("enter dx");
        int dx = in.nextInt();
        System.out.println("enter dy");
        int dy = in.nextInt();
        
        final int ROWS = 100;
        final int COLS = 100;
        radar = new Radar(ROWS, COLS,dx,dy);
        radar.setNoiseFraction(.01);
        boolean inRange = radar.scan();
        
        
        
        JFrame frame = new JFrame();
        
        frame.setTitle("Signals in Noise Lab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // a frame contains a single component; create the radar component and add it to the frame
        RadarComponent component = new RadarComponent(radar);
        frame.add(component);
        
        // set the size of the frame to encompass the contained component
        frame.pack();
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
        
        // perform 10 scans of the radar wiht a slight pause between each
        // after each scan, instruct the Java Run-Time to redraw the window
        
        int count = 0;//dx dy could be 0,0 so hard stop at 25 runs
        while(inRange && count<25)
        {
            Thread.sleep(100); // sleep 100 milliseconds (1/10 second)
            
            inRange = radar.scan();
   
            frame.repaint();
            
            count++;
        }
        int[] arr = new int[2];
        arr = radar.getDxDy();
        System.out.println("dx = "+arr[0]);
        System.out.println("dy = "+arr[1]);
    }
    
    public RadarViewer(int dx, int dy)throws InterruptedException
    {
        // create the radar, set the monster location, and perform the initial scan
        
        
        final int ROWS = 100;
        final int COLS = 100;
        radar = new Radar(ROWS, COLS,dx,dy);
        radar.setNoiseFraction(.01);
        boolean inRange = radar.scan();
        
        
        
        JFrame frame = new JFrame();
        
        frame.setTitle("Signals in Noise Lab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // a frame contains a single component; create the radar component and add it to the frame
        RadarComponent component = new RadarComponent(radar);
        frame.add(component);
        
        // set the size of the frame to encompass the contained component
        frame.pack();
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
        
        // perform 10 scans of the radar wiht a slight pause between each
        // after each scan, instruct the Java Run-Time to redraw the window
        int count = 0;//dx dy could be 0,0 so hard stop at 25 runs
        while(inRange && count<25)
        {
            Thread.sleep(100); // sleep 100 milliseconds (1/10 second)
            
            inRange = radar.scan();
   
            frame.repaint();
            
            count++;
        }
        
       
    }

    public int[] getdxdy()
    {
        int[] arr = new int[2];
        arr = radar.getDxDy();
        return arr;
    }
    
    public static void main(String[] args) throws InterruptedException
    {
        RadarViewer radarViewer = new RadarViewer();
    }
}
