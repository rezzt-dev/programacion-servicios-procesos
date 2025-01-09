/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.servidorBBDD;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class Servidor {
  private static final int PUERTO = 9876;
  
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
      System.out.println(" > Servidor fucionando... Puerto: " + PUERTO);
      
      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("  - Conexion establecida con el servidor.");
        
        new ManejoClientes(clientSocket).start();
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
