/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jgc.ejerciciojugador;

/**
 *
 * @author rezzt
 */
public class EjercicioJugador {
  public static void main(String[] args) {
    try {
      int totalJugadores = 3;
      Arbitro arbitro = new Arbitro(3);
      
      Jugador[] listJugador = new Jugador[totalJugadores];
      
      for (int i=0; i<listJugador.length; i++) {
        listJugador[i] = new Jugador(i+1, arbitro);
        listJugador[i].start();
      }
      
      for (Jugador jugador : listJugador) {
        jugador.join();
      }
    } catch (Exception e) {
      System.out.println(" > Ha ocurrido un error inesperado.");
    }
  }
}
