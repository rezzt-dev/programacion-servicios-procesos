/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciofacturas;

import java.util.Random;

/**
 *
 * @author rezzt
 */
public class Empleado extends Thread {
  private final int id;
  private Tarifa tarifa;
  private int numFacturasGen;
  
  public Empleado (int inputId, Tarifa inputTarifa) {
    this.id = inputId;
    this.tarifa = inputTarifa;
    this.numFacturasGen = 0;
  }
  
  @Override
  public void run () {
    long tiempoInicio = System.currentTimeMillis();
    Random random = new Random();
    
    while ((System.currentTimeMillis() - tiempoInicio) < 4000) {
      try {
        int consumo = random.nextInt(50) + 1;
        String factura = tarifa.generarFactura(consumo);
        
        String[] partes = factura.split(":");
        double importe = Double.parseDouble(partes[1].replace(",", "."));
        System.out.printf("Factura %s con importe %.2f€ generada por el Empleado %d.%n", partes[0], importe, id);
        this.numFacturasGen++;
        
        Thread.sleep(1000L * id);
      } catch (InterruptedException ex) {
        System.out.println(" > Se ha interrumpido el hilo que se esta ejecutando.");
        throw new RuntimeException();
      }
    }
  }

  public int getNumFacturasGen() {
    return numFacturasGen;
  }
  
  public int getIdEmple() {
    return id;
  }
}
