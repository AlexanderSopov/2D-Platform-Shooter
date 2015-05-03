/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.entity;

/**
 *
 * @author Rasmus
 */
public class vec2 {

   public float x, y;

   public vec2(float x, float y) {
      this.x = x;
      this.y = y;
   }

   public int getX() {
      return (int) x;
   }

   public int getY() {
      return (int) y;
   }

}