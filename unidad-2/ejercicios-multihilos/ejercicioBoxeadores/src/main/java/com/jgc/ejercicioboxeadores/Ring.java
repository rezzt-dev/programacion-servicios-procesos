/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicioboxeadores;

/**
 *
 * @author rezzt
 */
public class Ring {
  private static int numCombates = 0;
  
  public static void incrementarCombates() {
    numCombates++;
  }
  
  public static int getNumCombates () {
    return numCombates;
  }
}
