/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciocarreras;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rezzt
 */
public class Carrera {
  private int distanciaTotal;
  private boolean carreraTerminada;
  private List<Coche> listaCoches;
  
  public Carrera () {
    this.distanciaTotal = new Random().nextInt(1000) + 1;
    this.carreraTerminada = false;
    this.listaCoches = new ArrayList<>();
    
    System.out.println(" > Distancia Total de la Carrera: " + this.distanciaTotal + " metros.");
  }
  
  public synchronized void agregarCoche (Coche inputCoche) {
    listaCoches.add(inputCoche);
  }
  
  public synchronized boolean actualizarPosicion (Coche inputCoche, int distanciaRecorrida) {
    if (!carreraTerminada) {
      if (distanciaRecorrida >= this.distanciaTotal) {
        carreraTerminada = true;
        System.out.println("  - ¡¡¡¡ El coche " + inputCoche.getNombre() + " ha ganado la carrera !!!!");
        return true;
      } else {
        double porcentaje = (double) distanciaRecorrida / distanciaTotal * 100;
        System.out.printf("El coche ½s ha recorrido %.2f½½ de la distancia total ½n", inputCoche.getNombre(), porcentaje);
      }
    }
    
    return false;
  }
  
  public boolean isCarreraTerminada () {
    return this.carreraTerminada;
  }
  
  public List<Coche> getListaCoches () {
    return this.listaCoches;
  }
}
