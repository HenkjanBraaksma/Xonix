/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xonix;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 *
 * @author Jeroen
 */
public class ScoreView extends javax.swing.JPanel
{
        final private javax.swing.JLabel level;
        final private javax.swing.JLabel time;
        final private javax.swing.JLabel lives;
        final private javax.swing.JLabel cscore;
        final private javax.swing.JLabel rscore;
        //final private Container content = getContentPane();
        //final private JProgressBar progressBar = new JProgressBar();
    /**
     * layout and display of the score (above the gamefield itself)
     */
        public ScoreView ()
        {
            this.setLayout (new javax.swing.BoxLayout (this, javax.swing.BoxLayout.X_AXIS));
            this.setBorder (javax.swing.BorderFactory.createEmptyBorder (10, 0, 10, 0));
            level = new javax.swing.JLabel ("");
            this.add (level);
//            setBackground(Color.cyan);
            this.add (javax.swing.Box.createHorizontalGlue ());
            time = new javax.swing.JLabel ("");
            this.add (time);
            this.add (javax.swing.Box.createHorizontalGlue ());
            lives = new javax.swing.JLabel ("");
            this.add (lives);
            this.add (javax.swing.Box.createHorizontalGlue ());
            cscore = new javax.swing.JLabel ("");
            this.add (cscore);
//            progressBar.setValue(0);
//            progressBar.setStringPainted(true);
//            Border border = BorderFactory.createTitledBorder("  To next level:  ");
//            progressBar.setBorder(border);
//            content.add(progressBar, BorderLayout.NORTH);
//            setSize(300, 100);
//            setVisible(true);
            this.add (javax.swing.Box.createHorizontalGlue ());
            rscore = new javax.swing.JLabel ("");
            this.add (rscore);
        }
        /**
         * updates the display of the score
         */
        public void update ()
        {
            this.level.setText ("Current level: " + GameWorld.getInstance().state.getLevel ());
            this.time.setText ("Remaining time: " + (int) GameWorld.getInstance().state.getClock ());
            this.lives.setText ("Lives left: " + GameWorld.getInstance().state.getLives ());
            this.cscore.setText ("Current score: " + GameWorld.getInstance().state.getcscore ());
            this.rscore.setText ("Required score: " + GameWorld.getInstance().state.getrscore ());
            //this.progressBar.setValue(GameWorld.getInstance().state.getcscore () * 100 /GameWorld.getInstance().state.getrscore () );
        }
}
