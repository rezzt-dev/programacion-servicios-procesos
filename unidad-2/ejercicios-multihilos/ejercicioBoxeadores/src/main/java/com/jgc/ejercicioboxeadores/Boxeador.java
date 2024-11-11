/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicioboxeadores;

/**
 *
 * @author rezzt
 */
public class Boxeador extends Thread {
  private String nombre;
  private Boxeador rival;
  
  private int golpesDados = 0;
  private int golpesRecibidos = 0;
  private boolean estaNoqueado = false;
  
  public Boxeador (String inputNombre) {
    this.nombre = inputNombre;
  }
  
  @Override
  public void run () {
    while (Ring.getNumCombates() < 100) {
      if (this.estaNoqueado) {
        System.out.println(" -- " + this.nombre + " esta noqueado.");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ex) {
          ex.getMessage();
        }
        
        this.estaNoqueado = false;
      } else {
        combatir();
        
        try {
          Thread.sleep(2000);
        } catch (InterruptedException ex) {
          ex.getMessage();
        }
      }
    }
  }
  
  private void combatir() {
    System.out.println("  + " + this.nombre + " pego a " + rival.getNombre());
    golpesDados++;
    rival.recibirGolpe();
  }
  
  public synchronized void recibirGolpe () {
    this.golpesRecibidos++;
    if ((golpesRecibidos % 3) == 0) {
      this.estaNoqueado = true;
    }
  }

  public String getNombre() {
    return nombre;
  }
  
  public String getEstadisticas () {
    return this.nombre + " { Golpes Dados " + this.golpesDados + " | Golpes Recibidos " + this.golpesRecibidos + " }"; 
  }
  
  public void setRival (Boxeador inputRival) {
    this.rival = inputRival;
  }
}
