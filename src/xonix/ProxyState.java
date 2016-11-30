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
public class ProxyState implements State {

    private RealState s;
    
    public ProxyState (RealState s)
    {
        this.s = s;
    }

    public int getLevel ()
    {
        return s.getLevel();
    }
    
    public int getLives ()
    {
        return s.getLives();
    }

    public float getClock ()
    {
        return s.getClock();
    }

    public int getcscore ()
    {
        return s.getcscore();
    }

    public int getrscore ()
    {
        return s.getrscore();
    }

    public boolean isGameOver ()
    {
        return s.isGameOver();
    }

    @Override
    public String toString ()
    {
        return "Current level=" + this.getLevel ()
                + " Remaining lives=" + this.getLives ()
                + " Remaining time=" + this.getClock ()
                + " Current score=" + this.getcscore ()
                + " Required score=" + this.getrscore ();
    }
}
