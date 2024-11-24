/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciograndesalmacenes;

/**
 *
 * @author rezzt
 */
public class Almacen {
  private int numProductos;
  
  public Almacen (int nProductos) {
    this.numProductos = nProductos;
  }
  
  public synchronized boolean cogerProducto () {
    boolean booProducto = false;
    
    if (this.numProductos > 0) {
      this.numProductos--;
      booProducto = true;
    }
    
    return booProducto;
  }
}
