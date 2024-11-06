/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package es.juan.dos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * @version 1.0
 * Created on 24 oct 2024
 */

 // lanza y maneja hilos pares e impares
public class LanzarParImpar {
  public static void main (String[] args) {
    try {
      Secuenciador secuenciador = new Secuenciador();
      Hilo hiloPar = new Hilo(true, secuenciador);
      Hilo hiloImpar = new Hilo(false, secuenciador);

      List<Hilo> hilos = new ArrayList<>();
      hilos.add(hiloPar);
      hilos.add(hiloImpar);

      for (Hilo hilo : hilos) {
        hilo.start();
      }
      
      Thread.sleep(1000);
      
      for (int i=0; i<10; i++) {
        secuenciador.siguiente();
        Thread.sleep(1000);
        
        hiloPar.join();
        hiloImpar.join();
      }
      
    } catch (InterruptedException ex) {
      System.err.println("Error producido por: " + ex.getCause());
    }
  }
}
