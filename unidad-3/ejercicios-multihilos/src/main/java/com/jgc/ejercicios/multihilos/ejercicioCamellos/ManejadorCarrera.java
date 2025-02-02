/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.ejercicioCamellos;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author rezzt
 */
public class ManejadorCarrera extends Thread {
  private HashMap<Socket, Integer> camellos;
  private final static int MAX_METROS = 100;
  private int random;
 
 //-------------------------------------------------------------------------------------------->
  public ManejadorCarrera (HashMap<Socket, Integer> inputCamellos) {
    this.camellos = inputCamellos;
  }
  
 //-------------------------------------------------------------------------------------------->
  @Override
  public void run () {
    while (getFinalizado()) {
      String estadoCarrera = avanzar();
    }
  }
  
 //-------------------------------------------------------------------------------------------->
  private boolean getFinalizado () {
    boolean finalizado = false;
    
    for (Map.Entry<Socket, Integer> aux : camellos.entrySet()) {
      if (aux.getValue() >= MAX_METROS && (finalizado == false)) {
        finalizado = true;
        System.out.println(" -> El ganador es: " + aux.getKey().getInetAddress());
      }
    }
    
    return finalizado;
  }
  
  private String avanzar () {
    String mensajeReturn = "";
    
    for (Map.Entry<Socket, Integer> aux : camellos.entrySet()) {
      aux.setValue(aux.getValue() + (new Random().nextInt(0, 10) + 1));
      mensajeReturn = mensajeReturn + "\n - Cliente: " + aux.getKey().getInetAddress() + " - Metros: " + aux.getValue();
    }
    
    return mensajeReturn;
  }
}
