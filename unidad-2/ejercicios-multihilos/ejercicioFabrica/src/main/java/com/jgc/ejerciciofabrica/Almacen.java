/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciofabrica;

/**
 *
 * @author rezzt
 */
public class Almacen {
  private int numLadrillos;
  private static final int CAPACIDAD_MAXIMA = 6000;
  
  public Almacen () {
    this.numLadrillos = 0;
  }
  
  public synchronized boolean almacenar (int inputCantidad) {
    boolean operacionRealizada;
    
    if (numLadrillos + inputCantidad <= CAPACIDAD_MAXIMA) {
      numLadrillos += inputCantidad;
      System.out.println("Almacenados " + inputCantidad + " ladrillos. Total: " + numLadrillos);
      
      notifyAll();
      operacionRealizada = true;
    } else {
      operacionRealizada = false;
    }
    
    return operacionRealizada;
  }
  
  public synchronized boolean retirar (int inputCantidad) {
    boolean operacionRealizada;
    
    if (numLadrillos >= inputCantidad) {
      numLadrillos -= inputCantidad;
      System.out.println("Retirados " + inputCantidad + " ladrillos. Total: " + numLadrillos);
      
      notifyAll();
      operacionRealizada = true;
    } else {
      operacionRealizada = false;
    }
    
    return operacionRealizada;
  }
}
