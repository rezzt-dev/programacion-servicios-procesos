/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciofabrica;

/**
 *
 * @author rezzt
 */
public class Constructora {
  public static void main (String[] args) {
    Almacen almacen = new Almacen();
    Fabrica fabrica = new Fabrica(almacen);
    Obra obra1 = new Obra(almacen, 200, 4, " Obra 1");
    Obra obra2 = new Obra(almacen, 400, 2, " Obra 2");
    
    fabrica.start();
    obra1.start();
    obra2.start();
    
    try {
      fabrica.join();
      obra1.join();
      obra2.join();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
    
    System.out.println("Constructora cerrada.");
  }
}
