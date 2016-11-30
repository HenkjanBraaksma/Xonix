/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xonix;

/**
 * MovingObject with:
 * variables: speed, heading
 * function: nextlocation, get/set of speed and heading
 * @author Jeroen
 */
abstract class MovingObject extends BaseObject
{
    private java.awt.geom.Point2D.Float loc;
    private java.awt.Color color;
    private int heading;
    private float speed;
    /**
     * constructor of MovingObject
     * @param loc location
     * @param color
     * @param heading direction
     * @param speed 
     */
    public MovingObject (final java.awt.geom.Point2D.Float loc, final java.awt.Color color, final int heading, final float speed)
    {
        super(loc, color);
        setHeading (heading);
        setSpeed (speed);
    }
    /**
    * get Heading
    * @return 
    */
    public int getHeading ()
    {
        return heading;
    }
    /**
     * Set Heading
     * @param heading 
     */
    public final void setHeading (final int heading)
    {
        this.heading = heading;
    }
    /**
     * get Speed
     * @return 
     */
    public float getSpeed ()
    {
        return speed;
    }
    /**
     * Set Speed
     * @param speed 
     */
    public final void setSpeed (final float speed)
    {
        this.speed = speed;
    }
    /**
    * gives next location of movingobject
    * @param delta
    * @return new x and y coordinates
    */
    public java.awt.geom.Point2D.Float nextLocation (float delta)
    {
        double radians = Math.toRadians (getHeading ());
        float newx = getLocation ().x + delta * getSpeed () * (float) Math.cos (radians);
        if (newx < 0)
            newx = 0;
        else if (newx > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newx = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        float newy = getLocation ().y - delta * getSpeed () * (float) Math.sin (radians);
        if (newy < 0)
            newy = 0;
        else if (newy > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newy = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        return new java.awt.geom.Point2D.Float (newx, newy);
    }
}
