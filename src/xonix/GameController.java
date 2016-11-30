/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xonix;
import static xonix.GameWorld.GAME_TICK_DELAY;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeroen
 */

public class GameController
{
    private static GameController controller = new GameController();
    private GameWorld gw = GameWorld.getInstance();
    private GameView gv = GameView.getInstance();
    
    private javax.swing.Timer timer = new javax.swing.Timer(GAME_TICK_DELAY, new TimeTickUpdate());
    
    private GameController() 
    {
        gw.addObserver(gv);
        SetTimer();
        setMenu();
    }
    abstract class Command extends javax.swing.AbstractAction implements java.awt.event.ActionListener 
    { 
        
    }
        public class GoNorth extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().car.setHeading(90);
            }
        }

        public class GoSouth extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().car.setHeading(270);
            }
        }

        public class GoEast extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().car.setHeading(0);
            }
        }

        public class GoWest extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().car.setHeading(180);
            }
        }

        public class IncreaseCarspeed extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().car.setSpeed (GameWorld.getInstance().car.getSpeed () + 5);
            }
        }

        public class DecreasCarspeed extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().car.setSpeed (GameWorld.getInstance().car.getSpeed () - 5);
            }
        }

        public class AddMonsterball extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().mbs.add(new MonsterBall (new java.awt.geom.Point2D.Float (GameWorld.getInstance().random.nextInt (GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15, GameWorld.getInstance().random.nextInt (GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15), GameWorld.MONSTER_COLOR, GameWorld.getInstance().random.nextInt (360), GameWorld.getInstance().random.nextFloat () * 100 + 10, 6));
            }
        }

        public class NewTimeticket extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().tts.add (new TimeTicket (new java.awt.geom.Point2D.Float (GameWorld.getInstance().random.nextInt (GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15, GameWorld.getInstance().random.nextInt (GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15), GameWorld.TICKET_COLOR, GameWorld.TTIME_START, 7, 7));
            }
        }
        public class CarGotNewSquare extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().car.redsquare();
            }
        }
        
        public class CollisionMonsterball extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                for (MonsterBall mb : GameWorld.getInstance().mbs)
                {
                    mb.Collision(GameWorld.getInstance().fss);
                }
            }
        }
        
        public class CollisionTimeticket extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().TimeTicketCollision();
            }
        }
        
        public class Switchbombstrategies extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                for (MonsterBall mb : GameWorld.getInstance().mbs)
                {
                    mb.Collision(GameWorld.getInstance().fss);
                    mb.setHoming((mb.getHoming() +1) % 4);
                }
            }
        }
                
        public class NewGroupOfSquares extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                GameWorld.getInstance().car.fillAllSquares(GameWorld.getInstance().fss, GameWorld.getInstance().rstate);
            }
        }
        
        public class AboutGame extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, "XONIX IS EEN XONIX SPEL, ZOEK MAAR OP GOOGLE OFZO", "ABOUT XONIX ", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        public class QuitGame extends Command
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent e)
            {
                 System.exit(0);
            }
        }
        
    private void setMenu ()
    {
        javax.swing.JMenuBar menuBar;
        javax.swing.JMenu menu;
        javax.swing.JMenuItem menuItem;
        menuBar = new javax.swing.JMenuBar ();
        menu = new javax.swing.JMenu ("File");
        menuItem = new javax.swing.JMenuItem ("New");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("Save");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("Undo");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("Sound");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem (new AboutGame());
        menuItem.setText("About");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem (new QuitGame());
        menuItem.setText("Quit");
        menu.add (menuItem);
        menuBar.add (menu);
        menu = new javax.swing.JMenu ("Command");
        menuItem = new javax.swing.JMenuItem ("Add bomb");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem (new AddMonsterball());
        menuItem.setText("Add Monsterball");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem (new NewTimeticket());
        menuItem.setText("Add TimeTicket");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem (new Switchbombstrategies());
        menuItem.setText("Switch bombstrategies");
//        menuItem = new javax.swing.JMenuItem ("Switch bombstrategies ");
        menu.add (menuItem);
        menuBar.add (menu);
        gv.setJMenuBar (menuBar);
    }
        
        public void KeyCommand (int keycode)
        {
            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_W, 0),"GoNorth");
            gv.map.getActionMap ().put ("GoNorth", new GoNorth());

            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_A, 0),"GoWest");
            gv.map.getActionMap ().put ("GoWest", new GoWest());

            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_S, 0),"GoSouth");
            gv.map.getActionMap ().put ("GoSouth", new GoSouth());

            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_D, 0),"GoEast");
            gv.map.getActionMap ().put ("GoEast", new GoEast());

            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_D, 0),"GoEast");
            gv.map.getActionMap ().put ("GoEast", new GoEast());

            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_I, 0),"IncreaseCarspeed");
            gv.map.getActionMap ().put ("IncreaseCarspeed", new IncreaseCarspeed());

            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_L, 0),"DecreasCarspeed");
            gv.map.getActionMap ().put ("DecreasCarspeed", new DecreasCarspeed());

            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_B, 0),"AddMonsterball");
            gv.map.getActionMap ().put ("AddMonsterball", new AddMonsterball());

            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_M, 0),"NewTimeticket");
            gv.map.getActionMap ().put ("NewTimeticket", new NewTimeticket());
            
            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_SPACE, 0),"Switchbombstrategies");
            gv.map.getActionMap ().put ("Switchbombstrategies", new Switchbombstrategies());
            
            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_Q, 0),"QuitGame");
            gv.map.getActionMap ().put ("QuitGame", new QuitGame());
            
            gv.map.getInputMap (javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke (java.awt.event.KeyEvent.VK_P, 0),"AboutGame");
            gv.map.getActionMap ().put ("AboutGame", new AboutGame());
        }
    
    
    public class TimeTickUpdate extends Command
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            gw.update ((float) (GAME_TICK_DELAY / 1000.0));
        }
    }
    
    public void SetTimer() {
        timer.start();
    }
    
    public static GameController getInstance() {
        return controller;
    }
}

