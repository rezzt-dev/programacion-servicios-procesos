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
public class SimuladorAscensor {
  public static void main(String[] args) {
    Ascensor ascensor = new Ascensor(0, 10); // Ascensor inicia en planta baja, edificio de 10 pisos

    Usuario usuario1 = new Usuario("Usuario 1", 5, ascensor);
    Usuario usuario2 = new Usuario("Usuario 2", 3, ascensor);
    Usuario usuario3 = new Usuario("Usuario 3", 7, ascensor);

    usuario1.start();
    usuario2.start();
    usuario3.start();

    try {
      usuario1.join();
      usuario2.join();
      usuario3.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Simulación de ascensor completada.");
  }
}
