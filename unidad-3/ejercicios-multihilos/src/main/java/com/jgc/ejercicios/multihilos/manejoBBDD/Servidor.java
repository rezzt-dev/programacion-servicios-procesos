package com.jgc.ejercicios.multihilos.manejoBBDD;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
  private static final int PORT = 9876;
  
  
  public static void main (String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println(" > Servidor encendido, salida por puerto: " + PORT);
      
      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("  - Conexion establecida con el servidor.");

        new ClientManage(clientSocket).start();
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
