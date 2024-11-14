/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciocasino;

/**
 *
 * @author rezzt
 */
public class Croupier extends Thread {
  private Ruleta ruleta;
  private int ultimoNumero;
  private boolean juegoTerminado = false;
  
  public Croupier (Ruleta inputRule) {
    this.ruleta = inputRule;
  }
  
  @Override
  public void run () {
    while (!juegoTerminado) {
      try {
        Thread.sleep(3000);
        ultimoNumero = ruleta.girarRule();
        System.out.println(" > Numero sacado: " + ultimoNumero);
        
        synchronized (this) {
          notifyAll();
        }
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }
  
  public int getUltimoNumero () {
    return ultimoNumero;
  }
  
  public void terminarJuego () {
    juegoTerminado = true;
  }
}
