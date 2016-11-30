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
public class OperationScared implements Strategy
{
    @Override
    public double doOperation(float num1x, float num1y, float num2x, float num2y, int heading)
    {
        return (Math.atan2((num1x-num2x),((num1y-num2y)))) + (1.5 * Math.PI);
    }
}
