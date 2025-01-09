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
  public static void main(String[] args) {
    int port = 9876;
    
    try (ServerSocket servidor = new ServerSocket(port)) {
      System.out.println(" - Servidor encendido. Puerto: " + port);
      
      while (true) {
        Socket peticion = servidor.accept();
        System.out.println("  - Conexion establecida con el cliente.");
        
        new ManageCliente(peticion).start();
      }
      
    } catch (IOException ex) {
      System.err.println(" !Error al levantar la conexion del servidor.");
    }
  }
}
