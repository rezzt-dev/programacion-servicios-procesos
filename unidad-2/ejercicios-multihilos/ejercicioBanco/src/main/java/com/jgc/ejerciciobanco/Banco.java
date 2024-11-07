/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciobanco;

/**
 *
 * @author rezzt
 */
public class Banco {
  public static void main (String[] args) {
    Cuenta cuentaCompartida = new Cuenta();
    ClienteIngresa clienteIngresa = new ClienteIngresa(cuentaCompartida);
    ClienteCobra clienteCobra = new ClienteCobra(cuentaCompartida);
    
    clienteIngresa.start();
    clienteCobra.start();
    
    try {
      clienteIngresa.join();
      clienteCobra.join();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
    
    System.out.println("El banco cierra.");
  }
}
