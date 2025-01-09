/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.servidorBBDD.model;

import java.io.Serializable;

/**
 *
 * @author rezzt
 */
public class Operacion implements Serializable {
  public enum Metodo {
    CREAR, CONSULTAR_ALL, CONSULTAR_EMPLEADO
  }
  private Metodo metodo;
  private Empleado empleado;
  private long idEmpleado;
  
  public Operacion (Metodo inpuMetodo, Empleado inputEmpleado) {
    this.metodo = inpuMetodo;
    this.empleado = inputEmpleado;
  }
  public Operacion (Metodo inputMetodo, long inputIdEmpleado) {
    this.metodo = inputMetodo;
    this.idEmpleado = inputIdEmpleado;
  }
  
  public Metodo getMetodo () {
    return this.metodo;
  }
  public Empleado getEmpleado () {
    return this.empleado;
  }
  public long getIdEmpleado () {
    return this.idEmpleado;
  }
}
