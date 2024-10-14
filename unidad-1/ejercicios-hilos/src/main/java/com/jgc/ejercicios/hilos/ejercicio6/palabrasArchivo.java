/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio6;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 13 oct 2024
 */
public class palabrasArchivo implements Callable<infoArchivo>{
  private String nombreArchivo;
  private String palabraBuscada;

  public palabrasArchivo(String nombreArchivo, String palabraBuscada) {
    this.nombreArchivo = nombreArchivo;
    this.palabraBuscada = palabraBuscada;
  }

  @Override
  public infoArchivo call() throws Exception {
    infoArchivo datos = new infoArchivo(nombreArchivo, palabraBuscada);
    int numeroLinea = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
      String linea;
      while ((linea = reader.readLine()) != null) {
        numeroLinea++;
        String[] palabras = linea.split("\\s+");
        for (String palabra : palabras) {
          if (palabra.equals(palabraBuscada)) {
            datos.incrementarOcurrencias();
            datos.agregarLinea(numeroLinea, linea);
          }
        }
      }
    }
    return datos;
  }
}
