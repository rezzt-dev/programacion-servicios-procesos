/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jgc.ejerciciofacturas;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rezzt
 */
public class EjercicioFacturas {
  public static void main(String[] args) {
    Tarifa tarifa = new Tarifa();
    Empleado[] listEmple = new Empleado[1];
    
    for (int i=0; i<listEmple.length; i++) {
      listEmple[i] = new Empleado(i+1, tarifa);
      listEmple[i].start();
    }
    
    for (Empleado tempEmple : listEmple) {
      try {
        tempEmple.join();
      } catch (InterruptedException ex) {
        Logger.getLogger(EjercicioFacturas.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    for (int i=0; i<listEmple.length; i++) {
      System.out.println(" > El empleado " + listEmple[i].getIdEmple() + " genero " + listEmple[i].getNumFacturasGen() + " facturas.");
    }
  }
}
