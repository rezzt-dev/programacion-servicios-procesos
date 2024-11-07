/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciofabrica;

/**
 *
 * @author rezzt
 */
public class Obra extends Thread {
  private Almacen almacen;
  private int lote;
  private int tiempoDescanso;
  private String nombre;
  private long tiempoInicio;

  public Obra(Almacen almacen, int lote, int tiempoDescanso, String nombre) {
    this.almacen = almacen;
    this.lote = lote;
    this.tiempoDescanso = tiempoDescanso;
    this.nombre = nombre;
    this.tiempoInicio = System.currentTimeMillis();
  }
  
  @Override
  public void run () {
    while ((System.currentTimeMillis() - tiempoInicio) < 120000) {
      if (almacen.almacenar(lote)) {
        System.out.println(nombre + " retiro " + lote + " ladrillos.");
      }
      
      try {
        Thread.sleep(tiempoDescanso * 1000);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
    
    System.out.println(nombre + " cerrada.");
  }
}
