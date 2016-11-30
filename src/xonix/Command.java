/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xonix;

/**
 *
 * @author Jeroen
 */
abstract class Command extends javax.swing.AbstractAction implements java.awt.event.ActionListener 
{
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
}
