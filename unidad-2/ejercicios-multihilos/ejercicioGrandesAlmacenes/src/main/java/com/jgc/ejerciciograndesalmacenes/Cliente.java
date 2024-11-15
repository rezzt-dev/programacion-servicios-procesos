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
public class Cliente implements Runnable {
  private static final int MAX_INTENTOS = 10;
  private final int id;
  private final Tienda tienda;
  
  public Cliente (Tienda inputTienda, int inputId) {
    this.id = inputId;
    this.tienda = inputTienda;
  }
  
  @Override
  public void run () {
    for (int intento=0; intento < MAX_INTENTOS; intento++) {
      if (tienda.intentarEntrar()) {
        if (tienda.cogerProducto()) {
          System.out.println("Cliente " + id + ": cogi un producto.");
        } else {
          System.out.println("Cliente " + id + ": ops, cruce pero no cogi nada.");
        }
        
        tienda.salir();
        return;
      }
      
      try {
        Thread.sleep(500);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        return;
      }
    }
    
    System.out.println("Cliente" + id + ": lo intenté muchas veces y no pude");
  }
}
