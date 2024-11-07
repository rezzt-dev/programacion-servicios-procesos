/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciocarreras;

import java.util.List;

/**
 *
 * @author rezzt
 */
public class Principal {
  public static void main (String[] args) {
    try {
      Carrera carrera = new Carrera();
      
      Coche opel = new Coche("opel", carrera);
      Coche seat = new Coche("seat", carrera);
      Coche ford = new Coche("kia", carrera);
      
      carrera.agregarCoche(opel);
      carrera.agregarCoche(seat);
      carrera.agregarCoche(ford);
      
      opel.start();
      seat.start();
      ford.start();
      
      mostrarPodium(carrera.getListaCoches());
    } catch (Exception e) {
      System.out.println("Error en la carrera: " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  private static void mostrarPodium (List<Coche> inputListaCoches) {
    Coche mOro = inputListaCoches.get(0);
    Coche mPlata = inputListaCoches.get(1);
    Coche mBronze = inputListaCoches.get(2);
    
    for (Coche coche : inputListaCoches) {
      if (coche.getDistanciaRecorrida() > mOro.getDistanciaRecorrida()) {
        mBronze = mPlata;
        mPlata = mOro;
        mOro = coche;
      } else if (coche.getDistanciaRecorrida() > mBronze.getDistanciaRecorrida()) {
        mBronze = mPlata;
        mPlata = coche;
      } else {
        mBronze = coche;
      }
    }
    
    System.out.println("\n--- Podium ---");
    System.out.println(" Oro: " + mOro.getNombre());
    System.out.println(" Plata: " + mPlata.getNombre());
    System.out.println(" Bronze: " + mBronze.getNombre());
  }
}
