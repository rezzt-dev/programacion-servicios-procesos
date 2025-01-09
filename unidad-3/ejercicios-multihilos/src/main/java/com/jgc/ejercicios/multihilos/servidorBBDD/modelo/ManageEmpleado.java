/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.servidorBBDD.modelo;

import java.io.Serializable;

/**
 *
 * @author rezzt
 */
public class ManageEmpleado implements Serializable {
   // constantes & variables ->
  public enum Metodo {
    CREAR, CONSULTAR_ALL, CONSULTAR_EMPLEADO
  }
  
  private Metodo metodo;
  private Empleado empleado;
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // constructores ->
  public ManageEmpleado (Metodo inputMetodo, Empleado inputEmpleado) {
    this.metodo = inputMetodo;
    this.empleado = inputEmpleado;
  }
 
 //---------------------------------------------------------------------------------------------------------------------------->
   // getters & setters ->
  public Metodo getMetodo () {
    return this.metodo;
  }
  
  public Empleado getEmpleado () {
    return this.empleado;
  }
}
