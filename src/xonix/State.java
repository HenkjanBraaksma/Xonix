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
public interface State 
{
    //public static RealState getInstance();
    public int getLevel ();

    public float getClock ();

    public int getLives ();

    public int getcscore ();

    public int getrscore ();

    public boolean isGameOver ();
}
