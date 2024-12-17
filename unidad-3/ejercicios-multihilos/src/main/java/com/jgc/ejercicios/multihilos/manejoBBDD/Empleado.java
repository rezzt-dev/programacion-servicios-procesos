package com.jgc.ejercicios.multihilos.manejoBBDD;

import java.io.Serializable;

class Empleado implements Serializable {
  private long idEmpleado;
  private String nombre;
  private short edad;
  private String direccion;

  public Empleado() {}

  public Empleado(String nombre, short edad, String direccion) {
      this.nombre = nombre;
      this.edad = edad;
      this.direccion = direccion;
  }

  public Empleado(long idEmpleado, String nombre, short edad, String direccion) {
      this.idEmpleado = idEmpleado;
      this.nombre = nombre;
      this.edad = edad;
      this.direccion = direccion;
  }

  // Getters y Setters
  public long getIdEmpleado() { return idEmpleado; }
  public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public int getEdad() { return edad; }
  public void setEdad(short edad) { this.edad = edad; }
  public String getDireccion() { return direccion; }
  public void setDireccion(String direccion) { this.direccion = direccion; }

  @Override
  public String toString() {
      return "Empleado{" +
              "idEmpleado=" + idEmpleado +
              ", nombre='" + nombre + '\'' +
              ", edad=" + edad +
              ", direccion='" + direccion + '\'' +
              '}';
  }
}