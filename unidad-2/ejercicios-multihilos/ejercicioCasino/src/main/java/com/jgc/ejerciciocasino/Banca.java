/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciocasino;

/**
 *
 * @author rezzt
 */
public class Banca {
  private int saldo;
  
  public Banca (int saldoInicial) {
    this.saldo = saldoInicial;
  }
  
  public synchronized void recibirApuesta (int inputCant) {
    saldo += inputCant;
  }
  
  public synchronized boolean pagarPremio (int inputCant) {
    boolean premioDado = false;
    
    if (saldo >= inputCant) {
      saldo -= inputCant;
      premioDado = true;
    }
    
    return premioDado;
  }
  
  public int getSaldo() {
    return this.saldo;
  }
}
