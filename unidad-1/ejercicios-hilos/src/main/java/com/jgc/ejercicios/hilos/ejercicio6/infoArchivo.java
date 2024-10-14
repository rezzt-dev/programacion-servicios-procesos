/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio6;

import java.util.*;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 13 oct 2024
 */
public class infoArchivo {
private String nombreArchivo;
  private String palabraBuscada;
  private int ocurrencias;
  private Map<Integer, String> lineasConPalabra;

  public infoArchivo(String nombreArchivo, String palabraBuscada) {
    this.nombreArchivo = nombreArchivo;
    this.palabraBuscada = palabraBuscada;
    this.ocurrencias = 0;
    this.lineasConPalabra = new HashMap<>();
  }

  public void incrementarOcurrencias() {
    ocurrencias++;
  }

  public void agregarLinea(int numeroLinea, String contenidoLinea) {
    lineasConPalabra.put(numeroLinea, contenidoLinea);
  }

  public String getNombreArchivo() {
    return nombreArchivo;
  }

  public String getPalabraBuscada() {
    return palabraBuscada;
  }

  public int getOcurrencias() {
    return ocurrencias;
  }

  public Map<Integer, String> getLineasConPalabra() {
    return lineasConPalabra;
  }
}
