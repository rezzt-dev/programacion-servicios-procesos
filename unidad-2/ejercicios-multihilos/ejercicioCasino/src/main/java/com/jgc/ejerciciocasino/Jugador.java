/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciocasino;

import java.util.Random;

/**
 *
 * @author rezzt
 */
public class Jugador extends Thread {
  private static final int CANT_APUESTA = 50;
  
  private String nombre;
  private int saldo;
  private Banca banca;
  private Croupier croupier;
  private Random random = new Random();
  
  public Jugador (String inputNombre, int saldoInicial, Banca inputBanca, Croupier inputCroupier) {
    this.nombre = inputNombre;
    this.saldo = saldoInicial;
    this.banca = inputBanca;
    this.croupier = inputCroupier;
  }
  
  @Override
  public void run() {
    while (saldo >= CANT_APUESTA) {
      int apuesta = CANT_APUESTA;
      int numeroElegido = random.nextInt(10) + 1;
      System.out.println("  - " + nombre + " aposto al numero: " + numeroElegido);
      
      saldo -= apuesta;
      banca.recibirApuesta(apuesta);
      
      synchronized (croupier) {
        try {
          croupier.wait();
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
          return;
        }
      }
      
      if (croupier.getUltimoNumero() == numeroElegido) {
        int premio = 50 * 5;
        
        if (banca.pagarPremio(premio)) {
          saldo += premio;
          System.out.println(nombre + " gano " + premio + " euros!");
        } else {
          System.out.println(nombre + "gano pero la banca no tiene suficiente dinero para pagar.");
        }
      }
    }
    
    System.out.println(nombre + " se quedo sin dinero para apostar.");
  }
  
  public int getSaldo () {
    return saldo;
  }
  
  public String getNombre () {
    return nombre;
  }
}
