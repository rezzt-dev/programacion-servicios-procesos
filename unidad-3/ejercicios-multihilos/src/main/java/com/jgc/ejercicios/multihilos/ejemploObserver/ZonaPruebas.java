/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.ejemploObserver;

/**
 *
 * @author rezzt
 */
public class ZonaPruebas {
  public static void main(String[] args) {
     // crear el avion cebo ->
    Avion avionCebo = new Avion(1000, 100, "derecha");
    
     // crear el avion perseguidor ->
    AvionPerseguidor avionCaza = new AvionPerseguidor(2000, 100, "izquierda");
    avionCebo.agregarObservador(avionCaza);
    
     // simulacion del avion cebo ->
    avionCebo.subir();
    avionCebo.bajar();
    avionCebo.bajar();
    avionCebo.girar("izquierda");
    avionCebo.girar("derecha");
    avionCebo.acelerar();
    
     // mostrar el estado final del avion caza ->
    System.out.println("!Estado final de los aviones:");
    avionCebo.mostrarEstado();
    avionCaza.mostrarEstado();
  }
}
