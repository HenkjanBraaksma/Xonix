/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xonix;

/**
 * Checks if object has Nextlocation function
 * @author Jeroen
 */
public interface Moveable {
    public java.awt.geom.Point2D.Float nextLocation (float delta);
}
