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
public class Usuario extends Thread {
  private final int pisoDestino;
  private final Ascensor ascensor;
  private final String nombre;

  public Usuario(String nombre, int pisoDestino, Ascensor ascensor) {
    this.nombre = nombre;
    this.pisoDestino = pisoDestino;
    this.ascensor = ascensor;
  }

  @Override
  public void run() {
    System.out.println(nombre + " quiere ir al piso " + pisoDestino);
    ascensor.moverAscensor(pisoDestino);
    System.out.println(nombre + " ha llegado al piso " + pisoDestino);
  }
}
