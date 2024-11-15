/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicioproductorconsumidor;

import java.util.Random;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 15 nov 2024
 */
public class Productor extends Thread {
  private final Buffer buffer;
  private final Random random = new Random();
  
  public Productor (Buffer inputBuffer) {
    this.buffer = inputBuffer;
  }
  
  @Override
  public void run () {
    try {
      while (true) {
        int numero = random.nextInt(100);
        buffer.producir(numero);
        Thread.sleep(500);
      }
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }
}
