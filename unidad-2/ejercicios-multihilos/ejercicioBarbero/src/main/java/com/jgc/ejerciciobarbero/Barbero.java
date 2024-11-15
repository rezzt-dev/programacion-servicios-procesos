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
public class Barbero extends Thread {
  private final Barberia barberia;
  private final int id;
  
  public Barbero (Barberia inputBarberia, int inputId) {
    this.barberia = inputBarberia;
    this.id = inputId;
  }
  
  @Override
  public void run() {
    while (barberia.estaAbierta()) {
      try {
        Cliente cliente = barberia.siguienteCliente();
        if (cliente != null) {
          Thread.sleep(250); // Tiempo para atender al cliente
          System.out.println("Barbero" + id + " atendiendo cliente: " + cliente.getId() + " -- Cliente atendido por Barbero" + id);
          cliente.atender();
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    
    System.out.println("Barbero" + id + " termina su jornada.");
  }
}
