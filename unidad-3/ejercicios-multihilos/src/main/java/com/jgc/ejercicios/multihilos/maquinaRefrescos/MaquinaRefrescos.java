/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.maquinaRefrescos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class MaquinaRefrescos {
   // constantes & atributos ->
  private static final int PUERTO = 9876;
  static int bebidasTotales = 100;
  
 //---------------------------------------------------------------------------------------------------------------------------->
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
      System.out.println(" - Servidor en funcionamiento. Puerto utilizado: " + PUERTO);
      
      while (true) {
        Socket peticionCliente = serverSocket.accept();
        new ManejoPeticion(peticionCliente).start();
      }
      
    } catch (IOException ex) {
      System.err.println(" -> Ha ocurrido un problema con el servidor.");
    }
  }  
}
