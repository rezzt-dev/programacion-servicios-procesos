/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejerciciocuentatarea;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * @version 1.0
 * Created on 31 oct 2024
 */
public class Contador {
  private int cuenta = 0;
  // Método sincronizado para incrementar la cuenta
  public synchronized void incrementar() {
  cuenta++;
  }
  // Método para obtener el valor de la cuenta
  public synchronized int getCuenta() {
  return cuenta;
  }
}
