/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio7;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 14 oct 2024
 */
public class Aula extends Thread {
  private String nombre;
  private Saludar saludo;
  private boolean esProfesor;
  
  public Aula(String inputNombre, Saludar inputSaludo, boolean inputEsProfresor) {
    this.nombre = inputNombre;
    this.saludo = inputSaludo;
    this.esProfesor = inputEsProfresor;
  }
  
  @Override
  public void run() {
    if (esProfesor) {
      saludo.responderSaludo();
    } else {
      saludo.saludarProfe(nombre);
    }
  }
}
