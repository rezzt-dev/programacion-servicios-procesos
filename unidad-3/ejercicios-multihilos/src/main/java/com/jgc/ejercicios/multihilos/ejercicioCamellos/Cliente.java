/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.ejercicioCamellos;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class Cliente {
  public static void main(String[] args) {
    String hostname = "localhost";
    int puerto = 9876;
    
    try (Socket socket = new Socket(hostname, puerto);
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
      
      
      
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
  
  
}
