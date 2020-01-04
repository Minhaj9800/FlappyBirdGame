import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
/**
 * This class is the game driver panel. Here we set up the timer for our projects
 * create random rectangles pipe(same measurement of pipe in Pipe class). In this class
 * we also implement ActionListenr and MouseListener in this class.We also keep track 
 * of the score and high score as game start and over. In short almost full game handling
 * and design stuff happened in this class.
 * 
 * @author (Minhajur Rahman) 
 * @version (28 February 2019)
 */
public class GameDriverPanel extends JPanel
{
    private static final int INITIAL_PIPE_LOCATION = 300;
    private static final int PIPE_SEPARATION = 300;
    private static final int PIPE_SEPARATION_RANDOM = 50;

    private Random r = new Random();
    Timer timer;

    private ArrayList <Pipe> pipes;
    private Player player;
    private boolean playing = false;
    private boolean hasBeenStarted = false;

    private int score;
    private int highScore;
    
    public GameDriverPanel()// Constructor
    {
        this.setPreferredSize(new Dimension(Drawable.WIDTH, Drawable.HEIGHT));
        score = 0;
        pipes = new ArrayList <Pipe> ();
        player = new Player();
        //Generate statrting pipes
        generatePipes();

        // start timer
        timer = new Timer(15, new UpdateListener());

        this.setFocusable(true);

        // set up mouse listener
        this.addMouseListener(new ClickListener());
    }
    
    /**
     * Overriding Paint component method as required for our project
     */
    public void paintComponent(Graphics g)
    {
        if (!playing)
        {
            if(!hasBeenStarted)
            {
                g.drawString("Click Mouse to Start", Drawable.WIDTH/2 - 40, Drawable.HEIGHT/2 - 5);
            }
            else
            {
                g.drawString("Start Again!", Drawable.WIDTH/2 - 40, Drawable.HEIGHT/2 - 5);
            }
        }

        // draw all pipes and player
        player.draw(g);
        
        for(Pipe pipe : pipes)
        {
            pipe.draw(g);
        }

        //draw score

        //draw high score
        g.setColor(Color.black);
        g.drawString("Score: "+score, 5, 20);
        g.drawString("High Score: "+highScore, Drawable.WIDTH - 100, 20);
    }

    private class UpdateListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // move player, and pipes
            player.update();
            pipes.forEach(x -> x.update()); 

            //check if pipe has been passed and increment score

            //check for collisions
            //check for off screen
            if(player.isOffScreen())
            {
                gameOver();
            }

            for(Pipe p : pipes)
            {
                if(player.isHittingPipe(p))
                {
                    gameOver();
                }

                if(player.hasPassedPipe(p))
                {
                    score++;
                }
            }

            generatePipes();
            repaint();
        }
    }
    
    // inner class that implemet MouseListener.
    private class ClickListener implements MouseListener
    {
        public void mousePressed(MouseEvent e) {
            if (playing)
            {
                player.jump();
            }
            else
            {
                gameStart();
            }
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }
    }
    
    /**
     * Checking is the game is over or not and set up high score earned so far.
     */
    private void gameOver()
    {
        playing = false;
        timer.stop();
        if(score > highScore)
        {
            highScore = score;
        }
        //show score

        //ask play again
        //System.exit(0);
    }
    
    /**
     * Checking is the game start yet. If start then start the procedure.
     */
    private void gameStart()
    {
        if (hasBeenStarted)
        {
            pipes = new ArrayList <Pipe> ();
            player = new Player();
            score = 0;
            //Generate statrting pipes
            generatePipes();
        }
        hasBeenStarted = true;
        playing = true;
        timer.start();
    }
    
    /**
     * Generating pipes.
     */
    public void generatePipes()
    {
        if (pipes.isEmpty())
        {
            pipes.add(randomPipe(INITIAL_PIPE_LOCATION));
        }

        Pipe lastPipe;
        int newX, randomShift;

        while (pipes.get(pipes.size()-1).getX() < getWidth())
        {
            lastPipe = pipes.get(pipes.size()-1);

            randomShift = r.nextInt(2*PIPE_SEPARATION_RANDOM) - PIPE_SEPARATION_RANDOM;
            newX = lastPipe.getX() + PIPE_SEPARATION + randomShift;

            pipes.add(randomPipe(newX));
        }
    }
    
    /**
     * Creating random mehod for pipe.
     * @param x.
     * @return Pipe(400 - r.nextInt(150), r.nextInt(100) + 150, x);
     */
    public Pipe randomPipe(int x) {
        return new Pipe(400 - r.nextInt(150), r.nextInt(100) + 150, x);
    }
}
