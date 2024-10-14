/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio8;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 14 oct 2024
 */
public class Ascensor {
  private int pisoActual;
  private final int maxPisos;

  public Ascensor(int pisoInicial, int maxPisos) {
    this.pisoActual = pisoInicial;
    this.maxPisos = maxPisos;
  }
  
  public synchronized void moverAscensor(int pisoDestino) {
    System.out.println("Ascensor en piso " + pisoActual + ". Moviendo a piso " + pisoDestino);
        
    while (pisoActual != pisoDestino) {
      try {
        Thread.sleep(1000); // Simula 1 segundo por piso
        if (pisoActual < pisoDestino) {
          pisoActual++;
        } else {
          pisoActual--;
        }
          System.out.println("Ascensor en piso " + pisoActual);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    } 
    System.out.println("Ascensor llegó al piso " + pisoDestino);
  }
}
