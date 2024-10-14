/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio7;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 14 oct 2024
 */
public class UsarAula {
  public static void main(String[] args) {
    Saludar saludo = new Saludar();
    List<Thread> hilos = new ArrayList<>();

    // Crear alumnos
    for (int i = 1; i <= 6; i++) {
      hilos.add(new Aula("Alumno " + i, saludo, false));
    }

    // Crear profesor
    Thread profesor = new Aula("Profesor", saludo, true);

    // Lanzar hilos de alumnos
    for (Thread hilo : hilos) {
      hilo.start();
    }

    // Lanzar hilo del profesor
    profesor.start();

    // Esperar a que todos los hilos terminen
    try {
      for (Thread hilo : hilos) {
        hilo.join();
      }
      profesor.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Todos los hilos han terminado.");
  }
}
