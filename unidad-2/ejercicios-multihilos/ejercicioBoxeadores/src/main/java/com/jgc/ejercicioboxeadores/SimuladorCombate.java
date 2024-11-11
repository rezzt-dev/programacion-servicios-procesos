/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicioboxeadores;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rezzt
 */
public class SimuladorCombate {
  public static void main (String[] args) {
    Boxeador[] listBoxeadores = {
      new Boxeador("Boxeador 1"),
      new Boxeador("Boxeador 2"),
      new Boxeador("Boxeador 3"),
      new Boxeador("Boxeador 4")
    };
    
    Random random = new Random();
    for (Boxeador boxeador : listBoxeadores) {
      Boxeador rival = listBoxeadores[random.nextInt(listBoxeadores.length)];
      boxeador.setRival(rival);
      System.out.println("El rival de " + boxeador.getNombre() + " es " + rival.getNombre());
    }
    
    for (Boxeador boxeador : listBoxeadores) {
      boxeador.start();
    }
    
    for (Boxeador boxeador : listBoxeadores) {
      try {
        boxeador.join();
      } catch (InterruptedException ex) {
        Logger.getLogger(SimuladorCombate.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    for (Boxeador boxeador : listBoxeadores) {
      System.out.println(boxeador.getEstadisticas());
    }
  }
}
