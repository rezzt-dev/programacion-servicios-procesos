/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.sumaAscii;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class Servidor {
  public static void main(String[] args) {
    int puerto = 9876;
    
    try (ServerSocket serverSocket = new ServerSocket(puerto)) {
      System.out.println(" > Servidor inicado en el puerto: " + puerto);
      
      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("  - Cliente conectado.");
        new Thread(new Peticion(clientSocket)).start();
      }
    } catch (IOException ex) {
      System.err.println("  - Error en el servidor: " + ex.getMessage());
    }
  }
}
