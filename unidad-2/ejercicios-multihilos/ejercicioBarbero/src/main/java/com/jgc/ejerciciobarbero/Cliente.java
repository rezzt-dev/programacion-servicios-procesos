/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejerciciobarbero;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 15 nov 2024
 */
public class Cliente extends Thread {
  private final Barberia barberia;
  private final int id;
  private boolean atendido;
  
  public Cliente (Barberia inputBarberia, int inputId) {
    this.barberia = inputBarberia;
    this.id = inputId;
    this.atendido = false;
  }
  
  @Override
  public void run () {
    if (barberia.intentarSentarse(this)) {
      synchronized (this) {
        while (!atendido) {
          try {
            wait();
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
        }
      }
      
      System.out.println("Cliente " + id + " ya he sido atendido, me marcho.");
    } else {
      System.out.println("Cliente " + id + " no habia sillas libres, me marcho.");
    }
    
    barberia.clienteAtendido();
  }
  
  public synchronized void atender() {
    atendido = true;
    notify();
  }
  
  public int returnId() {
    return id;
  }
}
