/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciobanco;

/**
 *
 * @author rezzt
 */
public class ClienteCobra extends Thread {
  private Cuenta cuenta;
  private int totalCobrado;
  
  public ClienteCobra (Cuenta inputCuenta) {
    this.cuenta = inputCuenta;
    this.totalCobrado = 0;
  }
  
  @Override
  public void run () {
    while (totalCobrado < 6000) {
      if (cuenta.retirar(300)) {
        totalCobrado += 300;
      }
      
      try {
        Thread.sleep(3000);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
    
    System.out.println("Cliente Cobra se retira. Total cobrado: " + totalCobrado + " €.");
  }
}
