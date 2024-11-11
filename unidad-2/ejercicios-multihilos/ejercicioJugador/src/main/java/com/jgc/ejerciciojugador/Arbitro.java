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
public class Arbitro {
  private int numAdivinar;
  private int jugadorActual;
  private int totalJugadores;
  private boolean juegoTerminado;
  
  public Arbitro (int inputNumJugadores) {
    this.totalJugadores = inputNumJugadores;
    this.numAdivinar = new Random().nextInt(10) + 1;
    this.jugadorActual = new Random().nextInt(totalJugadores) + 1;
    this.juegoTerminado = false;
    
    System.out.println("Numero a adivinar: " + this.numAdivinar);
  }
  
  public synchronized boolean esMiTurno (int numJugador) {
    return (numJugador == jugadorActual && !juegoTerminado);
  }
  
  public synchronized boolean comprobarJugada (int numJugador, int jugada) {
    boolean jugadaRealizada = false;
    
    try {
      System.out.println(" > Jugador " + numJugador + " dice: " + jugada);
      
      if (jugada == numAdivinar) {
        System.out.println("  - Jugador " + numJugador + " gano, adivino el numero.");
        this.juegoTerminado = true;
        jugadaRealizada = true;
      } else {
        cambiarTurno();
        jugadaRealizada = false;
      }
    } catch (Exception e) {
      System.out.println(" > Error al comprobar jugada.");
    }
    
    return jugadaRealizada;
  }
  
  private void cambiarTurno () {
    jugadorActual = new Random().nextInt(totalJugadores) + 1;
    System.out.println(" > Le toca al Jugador " + jugadorActual);
  }
  
  public synchronized boolean isJuegoTerminado () {
    return this.juegoTerminado;
  }
}
