import java.awt.*;
import javax.swing.*;
/**
 * This the class for our player, with which we can play. We used happyFace.gif image icon
 * to represent the player. 
 * 
 * @author (Minhajur Rahman) 
 * @version (28 February 2019)
 */
public class Player implements Drawable
{
    // declaring all the necessary variables and constants 
    final double FALLING_RATE = 0.05;
    final int PLAYER_X = 100;
    final int PLAYER_WIDTH = 30;
    final int PLAYER_HEIGHT = 30;;
    final private ImageIcon image;
    double velocity;
    double y;

    public Player()// Constractor
    {
        image = new ImageIcon ("happyFace.gif");
        y = HEIGHT/2;
    }

    public void update()
    {
        y += velocity;
        velocity += FALLING_RATE;
    }
    
    /**
     * Player jumping.
     */
    public void jump()
    {
        velocity = -1;
    }
    
    /**
     * Draw player which is image icon.
     */
    public void draw(Graphics g)
    {
        image.paintIcon (null, g, PLAYER_X, (int) y);
    }
    
    /**
     * Checking if the player is go out off scrren.
     * @return boolean(true or flase) according to the circumstances.
     */
    public boolean isOffScreen()
    {
        if(y <= 0)
        {
            return true;
        }
        else if(y+PLAYER_HEIGHT > HEIGHT)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Checking if player hits the pipe or not.
     * @return boolean(true or flase) according to the circumstances.
     */
    public boolean isHittingPipe(Pipe pipe)
    {
        //Pipe has passed out of screen
        if (pipe.isFinished())
        {
            return false;
        }

        // Pipe is ahead of player
        if(pipe.getXStart() > PLAYER_X + PLAYER_WIDTH)
        {
            return false;
        }

        // Pipe is behind player but still visible
        if(pipe.getXEnd() < PLAYER_X)
        {
            return false;
        }

        if((y > pipe.getYTop()) && (y + PLAYER_HEIGHT < pipe.getYBottom()))
        {
            //System.out.println("in Gap");
            return false;
        }
        return true;
    }
    
    /**
     * Cheking if the player passed the pipe.
     * @return boolean(true or flase) according to the circumstances.
     */
    public boolean hasPassedPipe(Pipe pipe)
    {
        if (pipe.isScored()) return false;

        if(pipe.getXEnd() < PLAYER_X)
        {
            pipe.score();
            return true;
        }
        
        return false;
    }
}
