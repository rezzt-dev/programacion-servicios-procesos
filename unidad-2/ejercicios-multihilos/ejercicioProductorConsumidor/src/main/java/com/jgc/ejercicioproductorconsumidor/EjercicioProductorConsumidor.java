/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jgc.ejercicioproductorconsumidor;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 */
public class EjercicioProductorConsumidor {
  public static void main(String[] args) {
    Buffer buffer = new Buffer(5);
    
    Productor prod1 = new Productor(buffer);
    Productor prod2 = new Productor(buffer);
    Consumidor cons1 = new Consumidor(buffer);
    Consumidor cons2 = new Consumidor(buffer);
    
    prod1.start();
    prod2.start();
    cons1.start();
    cons2.start();
  }
}
