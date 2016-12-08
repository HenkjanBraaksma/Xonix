package xonix;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class GameView extends javax.swing.JFrame implements java.util.Observer
{

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof GameWorld) {
            update();
        }
    }
    
    private final javax.swing.JPanel all;
    public  ScoreView score = null;
    public final MapView map;

    /**
     * Makes the GameWindow
     */

    public GameView (MapView map, xonix.ScoreView score)
    {
        this.setTitle ("Xonix Game");
        all = new javax.swing.JPanel ();
        all.setLayout (new javax.swing.BoxLayout (all, javax.swing.BoxLayout.Y_AXIS));
        all.setBorder (new javax.swing.border.EmptyBorder (0, 30, 0, 30));
        this.score = score;
        all.add (score);
        this.map = map;
        map.setAlignmentX (CENTER_ALIGNMENT);
        all.add (map);
        this.add (all);
        this.pack ();
        this.setLocationRelativeTo (null);
        this.setDefaultCloseOperation (javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setSize (new java.awt.Dimension (630, 660));
        this.setResizable (false);
        this.setVisible (true);
    }

    public void update ()
    {
        score.update ();
        map.update ();
    }
}
