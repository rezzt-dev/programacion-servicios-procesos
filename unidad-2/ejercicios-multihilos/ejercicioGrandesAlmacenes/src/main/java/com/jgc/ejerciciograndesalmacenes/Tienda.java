/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejerciciograndesalmacenes;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 15 nov 2024
 */
public class Tienda {
  private boolean puertaOcupada = false;
  private int productos = 100;
  
  public synchronized boolean intentarEntrar () {
    boolean puedoEntrar;
    
    if (!puertaOcupada) {
      puertaOcupada = true;
      puedoEntrar = true;
    } else {
      puedoEntrar = false;
    }
    
    return puedoEntrar;
  }
  
  public synchronized void salir() {
    puertaOcupada = false;
  }
  
  public synchronized boolean cogerProducto() {
    boolean puedoCogerProducto;
    
    if (productos > 0) {
      productos--;
      puedoCogerProducto = true;
    } else { 
      puedoCogerProducto = false;
    }
    
    return puedoCogerProducto;
  }
}
