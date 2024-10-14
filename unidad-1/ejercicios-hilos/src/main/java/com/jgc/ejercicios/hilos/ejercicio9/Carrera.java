/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio9;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 14 oct 2024
 */
public class Carrera {
  public static void main(String[] args) {
    Testigo testigo = new Testigo();
    Atleta[] atletas = new Atleta[4];

    System.out.println("Comienza la carrera de relevos");
    long tiempoInicioCarrera = System.currentTimeMillis();

    // Crear y arrancar los atletas
    for (int i = 0; i < 4; i++) {
      atletas[i] = new Atleta(i + 1, testigo);
      atletas[i].start();
    }

    // Esperar a que todos los atletas terminen
    for (Atleta atleta : atletas) {
      try {
        atleta.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    long tiempoFinCarrera = System.currentTimeMillis();
    System.out.println("Carrera terminada. Tiempo total: " + (tiempoFinCarrera - tiempoInicioCarrera) / 1000.0 + " segundos");
  }
}
