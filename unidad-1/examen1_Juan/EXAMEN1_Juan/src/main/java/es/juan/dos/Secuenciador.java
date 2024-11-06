/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package es.juan.dos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * @version 1.0
 * Created on 24 oct 2024
 */
public class Secuenciador {
  private static Secuenciador instance;
  private int contador;
  
  
  public Secuenciador() {
    contador = 0;
  }
  
  public static Secuenciador getInstance() {
    if (instance == null) {
      instance = new Secuenciador();
    }
    return instance;
  }

   // si el contador es par, el hilo que es par se pone en espera
  public synchronized void esperarPar() {
    while (contador%2 == 0) {
      try {
        wait();
      } catch (InterruptedException ex) {
        Logger.getLogger(Secuenciador.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
   // si el contador es impar, el hilo que es impar se pone en espera
  public synchronized void esperarImpar() {
    while (contador%2 != 0) {
      try {
        wait();
      } catch (InterruptedException ex) {
        Logger.getLogger(Secuenciador.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
   // incrementa en 1 el contador y notifica a todos los hilo
  public synchronized void siguiente() {
    this.contador++;
    
    if (contador%2 == 0) {
      System.out.println("Contador es par:" + contador);
    } else {
      System.out.println("Contador es impar:" + contador);
    }
    
    notifyAll();
  }
}
