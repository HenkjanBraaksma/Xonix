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
public class Context {
   private Strategy strategy;

   public Context(Strategy strategy){
      this.strategy = strategy;
   }

   public double executeStrategy(float num1x, float num1y, float num2x, float num2y, int heading){
      return strategy.doOperation(num1x, num1y,num2x,num2y, heading);
   }
}
