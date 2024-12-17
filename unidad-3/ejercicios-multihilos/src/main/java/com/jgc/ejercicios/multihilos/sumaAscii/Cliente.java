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
import java.util.Scanner;

/**
 *
 * @author rezzt
 */
public class Cliente {
  public static void main(String[] args) {
    String host = "localhost";
    int puerto = 9876;
    
    try (Socket socket = new Socket(host, puerto);
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         Scanner scanner = new Scanner(System.in);
    ) {
      System.out.println(" > Conectando al servidor.");
      
      System.out.println("  - Ingresa el numero de palabras: ");
      int numLines = scanner.nextInt();
      scanner.nextLine();
      
      out.print(numLines);
      
      for (int i=0; i < numLines; i++) {
        System.out.println("   + Ingresa una palabra: ");
        String palabra = scanner.nextLine();
        out.print(palabra);
      }
      
      for (int i=0; i < numLines; i++) {
        String response = in.readLine();
        System.out.println(response);
      }
      
    } catch (IOException ex) {
      System.err.println("  - Error en el cliente: " + ex.getMessage());
    }
  }
}
