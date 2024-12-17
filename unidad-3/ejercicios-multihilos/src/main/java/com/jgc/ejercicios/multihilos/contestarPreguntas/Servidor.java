/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.contestarPreguntas;

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
      System.out.println(" > Servidor encendido, salida por puerto: " + puerto);
      
      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("  - Conexion establecida con el servidor.");

        new ManageCliente(clientSocket).start();
      }
    } catch (IOException ex) {
      System.err.println("  - Error al encender el servidor.");
    }
  }
}
