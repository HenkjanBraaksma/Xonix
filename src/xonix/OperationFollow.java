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
public class OperationFollow implements Strategy
{
    //radians = Math.atan2(((getLocation().x)-(GameWorld.getInstance().car.getLocation().x)),((getLocation().y)-GameWorld.getInstance().car.getLocation().y)) + (0.5 * Math.PI);
    public double doOperation(float num1x, float num1y, float num2x, float num2y, int heading)
    {
        return (Math.atan2((num1x-num2x),((num1y-num2y)))) + (0.5 * Math.PI);
    }
}
