/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciocarreras;

import java.util.Random;

/**
 *
 * @author rezzt
 */
public class Coche extends Thread {
  private String nombre;
  private int distanciaRecorrida;
  private Carrera carrera;
  
  public Coche (String inputNombre, Carrera inputCarrera) {
    this.nombre = inputNombre;
    this.carrera = inputCarrera;
    this.distanciaRecorrida = 0;
  }
  
  @Override
  public void run () {
    while (this.carrera.isCarreraTerminada()) {
      try {
        int avance = new Random().nextInt(50) + 1;
        this.distanciaRecorrida += avance;
        
        boolean haGanado = this.carrera.actualizarPosicion(this, distanciaRecorrida);
        if (haGanado) break;
        
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        System.out.println(" Error en el coche " + this.nombre + ": " + ex.getMessage());
        Thread.currentThread().interrupt();
      }
    }

  }

  public String getNombre() {
    return nombre;
  }

  public int getDistanciaRecorrida() {
    return distanciaRecorrida;
  }
}
