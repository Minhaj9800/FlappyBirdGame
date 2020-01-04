import javax.swing.*;
/**
 * This the class with main method where we can play our game.
 * 
 * @author (Minhajur Rahman) 
 * @version (28 February 2019)
 */
public class PlayHere
{
   public static void main (String[] args)
    {
        JFrame f = new JFrame("Dodgy Pipe Game");
        f.setDefaultCloseOperation(3);
        f.add(new GameDriverPanel());
        f.pack();
        f.setVisible(true);
    }
}


