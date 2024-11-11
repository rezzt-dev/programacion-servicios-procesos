/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciofacturas;

/**
 *
 * @author rezzt
 */
public class Tarifa {
  private int contadorFacturas;
  
  public Tarifa () {
    this.contadorFacturas = 1;
  }
  
  public synchronized String generarFactura (int consumo) {
    String numFactura = "FAC" + contadorFacturas++;
    double importe = calcularImporte(consumo);
    return String.format("%s:%.2f", numFactura, importe);
  }
  
  private double calcularImporte (int inputConsumo) {
    double tasaBase;
    
    if (inputConsumo < 15) {
      tasaBase = 1.5;
    } else if (inputConsumo < 30) {
      tasaBase = 2.1;
    } else {
      tasaBase = 3.0; 
    }
    
    return (tasaBase * inputConsumo);
  }
}
