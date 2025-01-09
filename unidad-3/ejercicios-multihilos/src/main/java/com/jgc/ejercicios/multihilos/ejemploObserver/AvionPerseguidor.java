/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.ejemploObserver;

/**
 *
 * @author rezzt
 */
public class AvionPerseguidor implements AvionObserver {
   // constantes & variables ->
  private int altura;
  private int velocidad;
  private String direccion;
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // constructores ->
  public AvionPerseguidor (int inputAltura, int inputVelocidad, String inputDireccion) {
    this.altura = inputAltura;
    this.velocidad = inputVelocidad;
    this.direccion = inputDireccion;
  }
  
//---------------------------------------------------------------------------------------------------------------------------->
   // metodos publicos ->
  @Override
  public void actualizar (int inputAltura, int inputVelocidad, String inputDireccion) {
    this.altura = inputAltura;
    this.velocidad = inputVelocidad;
    this.direccion = inputDireccion;
    
    System.out.println(" -> El avion perseguidor actualiza su estado: altura-" + altura + " | velocidad-" + velocidad + " | direccion-" + direccion + ".");
  }
  
  public void mostrarEstado () {
    System.out.println(" -> Estado del avion perseguidor: altura-" + altura + " | velocidad-" + velocidad + " | direccion-" + direccion + ".");
  }
}
