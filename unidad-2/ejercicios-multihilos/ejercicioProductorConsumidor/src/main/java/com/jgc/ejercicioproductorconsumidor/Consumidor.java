/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicioproductorconsumidor;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 15 nov 2024
 */
public class Consumidor extends Thread {
  private final Buffer buffer;
  
  public Consumidor (Buffer inputBuffer) {
    this.buffer = inputBuffer;
  }
  
  @Override
  public void run () {
    try {
      while (true) {
        buffer.consumir();
        Thread.sleep(700);
      }
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }
}
