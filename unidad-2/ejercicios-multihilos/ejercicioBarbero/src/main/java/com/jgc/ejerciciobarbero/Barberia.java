/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejerciciobarbero;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 15 nov 2024
 */
public class Barberia {
  private static final int NUM_BARBEROS = 5;
  private static final int NUM_SILLAS = 10;
  private static final int NUM_CLIENTES = 50;
  
  private final Cliente[] sillasEspera;
  private int clientesEnEspera;
  private int clientesAtendidos;
  private boolean barberiaAbierta;
  
  public Barberia () {
    sillasEspera = new Cliente[NUM_SILLAS];
    clientesEnEspera = 0;
    clientesAtendidos = 0;
    barberiaAbierta = true;
  }
  
  public void iniciar () {
    for (int i=0; i<NUM_BARBEROS; i++) {
      Barbero barbero = new Barbero(this, i);
      barbero.start();
    }
    
    for (int i=0; i<NUM_CLIENTES; i++) {
      Cliente cliente = new Cliente(this, i);
      cliente.start();
    }
    
    try {Thread.sleep(10);} catch (InterruptedException ex) {
      Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public synchronized boolean intentarSentarse (Cliente inputCliente) {
    boolean puedeSentarse;
    
    if (clientesEnEspera < NUM_SILLAS) {
      sillasEspera[clientesEnEspera] = inputCliente;
      
      System.out.println("El cliente " + inputCliente.returnId() + " se ha sentado en la silla: " + clientesEnEspera);
      clientesEnEspera++;
      notify(); // notifica a los barberos de que hay un nuevo cliente
      puedeSentarse = true;
    } else {
      puedeSentarse = false;
    }
    
    return puedeSentarse;
  }
  
  public synchronized Cliente siguienteCliente() throws InterruptedException {
    Cliente returnCliente;
    
    while ((clientesEnEspera == 0) && (barberiaAbierta)) {
      wait();
    }
    
    if (!barberiaAbierta) {
      returnCliente = null;
    }
    
    returnCliente = sillasEspera[0];
    for (int i=0; i<clientesEnEspera; i++) {
      sillasEspera[i] = sillasEspera[i + 1];
    }
    
    clientesEnEspera--;
    sillasEspera[clientesEnEspera] = null;
    return returnCliente;
  }
  
  public synchronized void clienteAtendido() {
    clientesAtendidos++;
    
    if (clientesAtendidos == NUM_CLIENTES) {
      barberiaAbierta = false;
      System.out.println("Se han ido todos los clientes. Barberia cerrada.");
      notifyAll(); // despertar a todos los barberos para que terminen
    }
  }
  
  public boolean estaAbierta () {
    return barberiaAbierta || clientesEnEspera > 0;
  }
}
