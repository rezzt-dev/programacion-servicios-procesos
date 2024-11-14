/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciofilosofos;

/**
 *
 * @author rezzt
 */
public class Tenedor {
  public boolean tomado = false;
  
  public synchronized void tomar() throws InterruptedException {
    while (tomado) {
      wait();
    }
    
    tomado = true;
  }
  
  public synchronized void soltar() {
    tomado = false;
    notify();
  }
}
