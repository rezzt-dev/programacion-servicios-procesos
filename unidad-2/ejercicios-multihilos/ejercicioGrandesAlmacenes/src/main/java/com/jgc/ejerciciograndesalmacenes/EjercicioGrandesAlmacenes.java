/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jgc.ejerciciograndesalmacenes;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 */
public class EjercicioGrandesAlmacenes {
  public static void main(String[] args) {
    Tienda tienda = new Tienda();
    Thread[] clientes = new Thread[5];

    for (int i = 0; i < clientes.length; i++) {
        clientes[i] = new Thread(new Cliente(tienda, i+1));
        clientes[i].start();
    }

    for (Thread cliente : clientes) {
        try {
            cliente.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    System.out.println("Simulacion finalizada");
  }
}
