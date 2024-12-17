/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.sumaAscii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class Peticion implements Runnable {
  private final Socket clientSocket;
  
  public Peticion (Socket inputClient) {
    this.clientSocket = inputClient;
  }
  
  @Override
  public void run () {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
    ) {
      String line = in.readLine();
      int numLines = Integer.parseInt(line);
      
      for (int i=0; i < numLines; i++) {
        String palabra = in.readLine();
        int checksuma = calcularSuma(palabra);
        out.println(checksuma);
      }
      
    } catch (IOException | NumberFormatException ex) {
      System.err.println("  - Error manejando la peticion del cliente: " + ex.getMessage());
    } finally {
      try {
        clientSocket.close();
      } catch (IOException ex) {
        System.err.println("  - Error cerrando el socket");
      }
    }
  }
  
  private int calcularSuma (String inputLine) {
    int result = 0;
    for (char c : inputLine.toCharArray()) {
      result += (int) c;
    }
    
    return result;
  }
}
