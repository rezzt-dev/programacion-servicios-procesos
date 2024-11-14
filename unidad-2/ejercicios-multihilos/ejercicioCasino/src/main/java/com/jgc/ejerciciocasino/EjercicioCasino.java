/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jgc.ejerciciocasino;

/**
 *
 * @author rezzt
 */
public class EjercicioCasino {
  public static void main(String[] args) {
    Ruleta ruleta = new Ruleta();
    Banca banca = new Banca(5000);
    Croupier croupier = new Croupier(ruleta);
    
    Jugador[] listJugadores = new Jugador[4];
    for (int i=0; i < listJugadores.length; i++) {
      listJugadores[i] = new Jugador("Jugador " + (i+1), 500, banca, croupier);
    }
    
    croupier.start();
    for (Jugador jugador : listJugadores) {
      jugador.start();
    }
    
    for (Jugador jugador : listJugadores) {
      try {
        jugador.join();
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
    
    croupier.terminarJuego();
    try {
      croupier.join();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
    
    System.out.println("\n Resultados Finales: ");
    for (Jugador jugador : listJugadores) {
      System.out.println(jugador.getNombre() + ": " + jugador.getSaldo() + " euros.");
    }
    
    System.out.println(" > Banca: " + banca.getSaldo() + " euros.");
  }
}
