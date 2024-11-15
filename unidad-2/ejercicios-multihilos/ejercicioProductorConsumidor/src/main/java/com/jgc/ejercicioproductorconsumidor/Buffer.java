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
public class Buffer {
  private final int[] buffer;
  private int in = 0;
  private int out = 0;
  private int count = 0;
  private final int size;
  
  public Buffer (int inputSize) {
    this.size = inputSize;
    buffer = new int[inputSize];
  }
  
  public synchronized void producir (int inputItem) throws InterruptedException {
    while (count == size) {
      System.out.println(Thread.currentThread().getName() + " - Buffer lleno. Productor espera.");
      wait(1000); // 1s
    }
    
    buffer[in] = inputItem;
    in = (in + 1) % size;
    count++;
    
    System.out.println(Thread.currentThread().getName() + " produce: " + inputItem);
    notify();
  }
  
  public synchronized int consumir() throws InterruptedException {
    while (count == 0) {
      System.out.println(Thread.currentThread().getName() + " - Buffer vacio. Consumidor espera.");
      wait(2000);
    }
    
    int item = buffer[out];
    out = (out + 1) % size;
    count--;
    
    System.out.println(Thread.currentThread().getName() + " consume: " + item);
    notify();
    return item;
  }
}
