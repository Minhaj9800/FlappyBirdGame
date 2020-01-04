import java.awt.*;
import javax.swing.*;
/**
 * This class implement drawable interface for creating the pipe obstacles.For this projects we use rectangular 
 * pipe and set the color as green.We also set up this class as it checked the gap 
 * of the upper and bottom rectangle. We also checked the offScreen and onScreen.
 * Also implement the methods check for checking the game start and end and implement a method
 * to keep track of the score.
 * @author (Minhajur Rahman) 
 * @version (28 February 2019)
 */
public class Pipe implements Drawable
{
    // all the necessary variables and constants.
    private static final int PIPE_WIDTH = 50;
    private static final int PIPE_VELOCITY = 3;
    private static final Color PIPE_COLOR = Color.green;
    private int top;
    private int gap;
    private int x;

    private boolean finished = false;
    private boolean scored = false;

    public Pipe(int top, int gap, int x) // constructor
    {
        this.top = top;
        this.gap = gap;
        this.x = x;
    }
    
    /**
     * Update the position.
     */
    public void update()
    {
        x -= PIPE_VELOCITY;
        // if off screen remove;
    }
    
    /**
     * Checking if is on Screen or not.
     * @ return x+PIPE_WIDTH > 0;
     */
    public boolean isOnScreen()
    {
        return x + PIPE_WIDTH > 0;
    }
    
    /**
     * Draw rectangle
     * @param Graphics g.
     */
    public void draw(Graphics g)
    {  
        if (finished) return; // if finished done and stop here.

        g.setColor(PIPE_COLOR);
        // draw the bottom pipe
        g.fillRect(x, top, PIPE_WIDTH, HEIGHT - top);
        g.fillRect(x - 5, top, PIPE_WIDTH + 10, 20);

        // draw the top pipe
        g.fillRect(x, 0, PIPE_WIDTH, top - gap);
        g.fillRect(x - 5, top - gap - 10, PIPE_WIDTH + 10, 20);
    }
    
    /**
     * Get the x.
     * @return x
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Get get x
     * @return x-5.
     */
    public int getXStart()
    {
        return x-5;
    }
    
    /**
     * Get XEnd
     * @return x + PIPE_WIDTH.
     * 
     */
    public int getXEnd()
    {
        return x + PIPE_WIDTH;
    }
    
    /**
     * Get Ytop
     * @return (top-gap).
     */
    public int getYTop()
    {
        return (top - gap);
    }
    
    /**
     * Get Ybottom 
     * @return top;
     */
    public  int getYBottom()
    {
        return top;  
    }
    
    /**
     * Finish the proceedure.
     */
    public void finish()
    {
        finished = true;
    }
    
    /**
     * If the procedure is finished(true or false).
     * @return finished
     */
    public boolean isFinished()
    {
        return finished;
    }
    
    /**
     * keep track of the score.
     */
    public void score()
    {
        scored = true;
    }
    
    /**
     * Know the score
     * @return scored (true or false).
     */
    public boolean isScored()
    {
        return scored;
    }
}
