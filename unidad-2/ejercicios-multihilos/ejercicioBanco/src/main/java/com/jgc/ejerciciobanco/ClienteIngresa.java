/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciobanco;

/**
 *
 * @author rezzt
 */
public class ClienteIngresa extends Thread {
  private Cuenta cuenta;
  private long tiempoInicio;
  
  public ClienteIngresa (Cuenta inputCuenta) {
    this.cuenta = inputCuenta;
    this.tiempoInicio = System.currentTimeMillis();
  }
  
  @Override
  public void run() {
    while ((System.currentTimeMillis() - this.tiempoInicio) < 90000) {
      cuenta.ingresar(500);
      
      try {
        Thread.sleep(2000);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
    
    System.out.println("Cliente Ingresa se retira.");
  }
}
