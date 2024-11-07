/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciofabrica;

/**
 *
 * @author rezzt
 */
public class Fabrica extends Thread {
  private Almacen almacen;
  private int ladrillosFabricados;
  private static final int LOTE = 450;
  private static final int MAXIMO_LADRILLOS = 13500;
  
  public Fabrica (Almacen inputAlmacen) {
    this.almacen = inputAlmacen;
    this.ladrillosFabricados = 0;
  }
  
  @Override
  public void run () {
    while (ladrillosFabricados < MAXIMO_LADRILLOS) {
      if (almacen.almacenar(LOTE)) {
        ladrillosFabricados += LOTE;
        
        try {
          Thread.sleep(3000);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
    }
    
    System.out.println("Fabrica cerrada. Total fabricado: " + ladrillosFabricados);
  }
}
