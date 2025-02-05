/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.ejercicioCamellos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author rezzt
 */
public class ServidorCarrera {
  private final static int MIN_CLIENTES = 5;
  private final static int MAX_METROS = 100;
  
  public static void main(String[] args) {
    int puerto = 9876;
    
    HashMap<Socket, Integer> listaClientes = new HashMap<>();
    ManejadorCarrera manejador = new ManejadorCarrera(listaClientes);
    
    try (ServerSocket servidor = new ServerSocket(puerto)) {
      System.out.println(" -> Servidor encendido. Puerto: " + puerto);
      
      while (listaClientes.size() != MIN_CLIENTES) {
        Socket cliente = servidor.accept();
        System.out.println("  - Cliente: " + cliente.getInetAddress());
        
        listaClientes.put(cliente, 0);
      }
      
      
      
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
  
  private boolean getFinalizado (HashMap<Socket, Integer> listaClientes) {
    boolean finalizado = false;
    
    for (Map.Entry<Socket, Integer> aux : listaClientes.entrySet()) {
      if (aux.getValue() >= MAX_METROS && (finalizado == false)) {
        finalizado = true;
      }
    }
    
    return finalizado;
  }
  
  private String avanzar (HashMap<Socket, Integer> listaClientes) {
    String mensajeReturn = "";
    
    for (Map.Entry<Socket, Integer> aux : listaClientes.entrySet()) {
      aux.setValue(aux.getValue() + (new Random().nextInt(0, 10) + 1));
      mensajeReturn = mensajeReturn + "\n - Cliente: " + aux.getKey().getInetAddress() + " - Metros: " + aux.getValue();
    }
    
    return mensajeReturn;
  }
}
