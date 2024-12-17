package com.jgc.ejercicios.multihilos.manejoBBDD;

import java.io.Serializable;

public class Operacion implements Serializable {
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
