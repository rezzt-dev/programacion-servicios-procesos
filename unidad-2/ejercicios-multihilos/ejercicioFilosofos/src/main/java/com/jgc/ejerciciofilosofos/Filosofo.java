/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciofilosofos;

/**
 *
 * @author rezzt
 */
public class Filosofo extends Thread {
  private final int id;
  private final Tenedor izquierdo;
  private final Tenedor derecho;
  private boolean haComido;
  
  public Filosofo (int inputId, Tenedor inputTI, Tenedor inputTD) {
    this.id = inputId;
    izquierdo = inputTI;
    derecho = inputTD;
    haComido = false;
  }
  
  private void pensar () throws InterruptedException {
    System.out.println(" > El filosofo " + id + " esta pensado.");
    Thread.sleep((long) (Math.random() * 4000 + 1000)); //1-5 seg
  }
  
  private void comer () throws InterruptedException {
    System.out.println(" > El filosofo " + id + " esta comiendo.");
    Thread.sleep((long) (Math.random() * 4000 + 1000));
  }
  
  @Override
  public void run() {
    try {
      while (haComido == false) {
        pensar();
        
        if (id % 2 == 0) {
          izquierdo.tomar();
          derecho.tomar();
        } else {
          derecho.tomar();
          izquierdo.tomar();
        }
        
        comer();
        haComido = true;
        izquierdo.soltar();
        derecho.soltar();
      }
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }
}
