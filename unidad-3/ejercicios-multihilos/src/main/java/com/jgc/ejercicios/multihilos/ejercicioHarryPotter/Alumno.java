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
public class Alumno {
   // constantes & atributos ->
  private static final String HOST = "localhost";
  private static final int PUERTO = 9876;
  
 //---------------------------------------------------------------------------------------------------------------------------->
  public static void main(String[] args) {
    for (int i=0; i<12; i++) {
      new Thread(() -> {
        try (Socket peticion = new Socket(HOST, PUERTO);
              BufferedReader in = new BufferedReader(new InputStreamReader(peticion.getInputStream()));
              PrintWriter out = new PrintWriter(peticion.getOutputStream())) {
          System.out.println(" -> Entrando a la tienda de varitas magicas.");
          String respuesta;
          while ((respuesta = in.readLine()) != null) {
            System.out.println("  -> " + respuesta);
          }

        } catch (IOException ex) {
          System.err.println("!ERROR. Se ha producido un error en el cliente");
        }
      }).start();
    }
  }
}
