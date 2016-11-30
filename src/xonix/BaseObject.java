/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xonix;

/**
 * BaseObject for standard objects
 * @author Jeroen
 */
abstract class BaseObject {
    
    private java.awt.geom.Point2D.Float loc;
    private java.awt.Color color;
    
    public BaseObject(final java.awt.geom.Point2D.Float loc, final java.awt.Color color){
        setLocation (loc);
        setColor (color);
    }
    /**
     * get location
     * @return location
     */
    public java.awt.geom.Point2D.Float getLocation ()
    {
        return loc;
    }
    /**
     * set Location
     * @param loc 
     */
    public void setLocation (java.awt.geom.Point2D.Float loc)
    {
        this.loc = loc;
    }
    /**
     * get Color
     * @return color
     */
    public java.awt.Color getColor ()
    {
        return color;
    }
    /**
     * set Color
     * @param color 
     */
    public void setColor (final java.awt.Color color)
    {
        this.color = color;
    }

}
