/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciobanco;

/**
 *
 * @author rezzt
 */
public class Cuenta {
  private int saldo;
  private static final int SALDO_MAXIMO = 5000;
  
  public Cuenta () {
    this.saldo = 0;
  }
  
  public synchronized boolean ingresar (int inputCantidad) {
    boolean operacionRealizada = false;
    
    if (this.saldo + inputCantidad <= SALDO_MAXIMO) {
      this.saldo += inputCantidad;
      System.out.println("Ingreso de " + inputCantidad + " €. Saldo actual: " + this.saldo + " €.");
      operacionRealizada = true;
    } else {
      System.out.println("No se puede ingresar " + inputCantidad + " €. Porque excede el saldo maximo.");
      operacionRealizada = false;
    }
    
    return operacionRealizada;
  }
  
  public synchronized boolean retirar (int inputCantidad) {
    boolean operacionRealizada = false;
    
    if (this.saldo >= inputCantidad) {
      this.saldo -= inputCantidad;
      System.out.println("Retiro de " + inputCantidad + " €. Saldo actual: " + this.saldo + " €.");
      operacionRealizada = true;
    } else {
      System.out.println("No se puede retirar " + inputCantidad + " €. Porque es mayor al saldo actual.");
      operacionRealizada = false;
    }
    
    return operacionRealizada;
  }
}
