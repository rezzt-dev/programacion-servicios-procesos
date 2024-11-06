/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package es.juan.dos;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * @version 1.0
 * Created on 24 oct 2024
 */
public class Hilo extends Thread {
  private Secuenciador secuenciador;
  private boolean esPar; //sino es par es impar
  
  /**
   * creamos un objeto hilo dado si es par o impar y el secuenciador
   * @param esPar si es true = par | false = impar
   * @param inputSecuenciador secuenciador de entrada necesario para el run del hilo
   */
  public Hilo (boolean esPar, Secuenciador inputSecuenciador) {
    this.secuenciador = inputSecuenciador;
    this.esPar = esPar;
  }
  
  @Override
  public void run () {
    if (esPar == true) {
      secuenciador.esperarPar();
    } else {
      secuenciador.esperarImpar();
    }
  }
}
