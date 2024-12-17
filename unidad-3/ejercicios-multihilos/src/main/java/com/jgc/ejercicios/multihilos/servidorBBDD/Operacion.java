/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.servidorBBDD;

import com.jgc.ejercicios.multihilos.servidorBBDD.manage.Empleado;

/**
 *
 * @author rezzt
 */
public class Operacion {
  public enum Metodo {
    CREAR, CONSULTAR_ALL, CONSULTAR_EMPLEADO
  }
  
  private Metodo metodo;
  private Empleado empleado;
  private int idEmpleadoConsulta;
  
  public Operacion (Metodo inputMetodo, Empleado inputEmpleado) {
    this.metodo = inputMetodo;
    this.empleado = inputEmpleado;
  }
  
  public Operacion (Metodo inputMetodo, int inputIdEmpleado) {
    this.metodo = inputMetodo;
    this.idEmpleadoConsulta = inputIdEmpleado;
  }
  
  public Metodo getMetodo () { return metodo; }
  public Empleado getEmpleado () { return empleado; }
  public int getIdEmpleado () { return idEmpleadoConsulta; }
}
