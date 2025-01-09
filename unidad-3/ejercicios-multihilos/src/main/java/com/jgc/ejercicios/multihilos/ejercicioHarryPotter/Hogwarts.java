/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.multihilos.ejercicioHarryPotter;


import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.*;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 9 ene 2025
 */
public class Hogwarts {
   // constantes & atributos ->
  static final List<String> inventarioVaritas = Collections.synchronizedList(new ArrayList<>(Arrays.asList(
          "Varita de Saúco",
          "Varita de Acebo y Pluma de Fénix",
          "Varita de Vid y Pelo de Unicornio",
          "Varita de Fresno y Nervio de Dragón",
          "Varita de Cerezo y Pluma de Fénix",
          "Varita de Nogal y Pelo de Thestral",
          "Varita de Abeto y Pelo de Unicornio",
          "Varita de Sauce y Nervio de Dragón",
          "Varita de Espino y Pluma de Fénix",
          "Varita de Tejo y Pluma de Fénix"
  )));
  private static int PUERTO = 9876;
  
 //---------------------------------------------------------------------------------------------------------------------------->
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
      System.out.println(" -> Hogwarts abre sus puertas a los alumnos. Puerta: " + PUERTO);
      
      while (true) {
        Socket peticionCliente = serverSocket.accept();
        System.out.println("  -> Conexion establecia con un cliente.");
        new Thread(new ManejoPeticion(peticionCliente)).start();
      }
    } catch (IOException ex) {
      System.err.println("!ERROR. Se ha producido un error en el servidor.");
    }
  }
}
