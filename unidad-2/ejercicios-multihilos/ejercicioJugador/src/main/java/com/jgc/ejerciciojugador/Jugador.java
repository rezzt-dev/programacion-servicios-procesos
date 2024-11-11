/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciojugador;

import java.util.Random;

/**
 *
 * @author rezzt
 */
public class Jugador extends Thread {
  private int id;
  private Arbitro arbitro;
  
  public Jugador (int inputId, Arbitro inputArbitro) {
    this.id = inputId;
    this.arbitro = inputArbitro;
  }
  
  @Override
  public void run () {
    try {
      while (!arbitro.isJuegoTerminado()) {
        if (arbitro.esMiTurno(id)) {
          int numGen = new Random().nextInt(10) + 1;
          
          if (arbitro.comprobarJugada(id, numGen)) {
            break;
          }
        }
        
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      System.out.println(" > Se ha encontrado un problema inesperado.");
    }
  }
}
