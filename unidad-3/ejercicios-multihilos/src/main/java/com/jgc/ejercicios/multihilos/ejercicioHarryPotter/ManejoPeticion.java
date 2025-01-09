/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.multihilos.ejercicioHarryPotter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 9 ene 2025
 */
public class ManejoPeticion implements Runnable {
   // constantes & atributos ->
  private final Socket peticion;
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // constructores ->
  public ManejoPeticion (Socket inputPeticion) {
    this.peticion = inputPeticion;
  }
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // metodos publicos -> 
  @Override
  public void run () {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(peticion.getInputStream()));
            PrintWriter out = new PrintWriter(peticion.getOutputStream())) {
      out.println(" - Bienvenido a la tienda de varitas magicas de Hogwarts.");
      out.flush();
      
      String varitaAsignada;
      synchronized (Hogwarts.inventarioVaritas) {
        if (!Hogwarts.inventarioVaritas.isEmpty()) {
          varitaAsignada = Hogwarts.inventarioVaritas.remove(0);
        } else {
          varitaAsignada = null;
        }
      }
      
      if (varitaAsignada != null) {
        out.println("  -> Se te ha asignado la varita: " + varitaAsignada);
        out.flush();
      } else {
        out.println("  -> Lo sentimos, no hay varitas disponibles en este momento.");
        out.flush();
      }
      
    } catch (IOException ex) {
      System.err.println("!ERROR. Se ha producido un error al manejar la peticion.");
    } finally {
      try {
        peticion.close();
      } catch (IOException ex) {
        System.err.println("!ERROR. No se ha podido cerrar la peticion.");
      }
    }
  }
}
