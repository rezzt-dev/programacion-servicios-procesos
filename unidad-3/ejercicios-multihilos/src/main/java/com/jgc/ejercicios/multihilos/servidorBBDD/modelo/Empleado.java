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
public class Empleado implements Serializable {
   // constantes & atributos ->
  private int idEmple;
  private String nombre;
  private int edad;
  private String direccion;
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // constructores ->
  public Empleado () {}
  public Empleado (int inputIdEmple, String inputNombre, int inputEdad, String inputDireccion) {
    this.idEmple = inputIdEmple;
    this.nombre = inputNombre;
    this.edad = inputEdad;
    this.direccion = inputDireccion;
  }
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // getters & setters ->

  public int getIdEmple() {
    return idEmple;
  }

  public void setIdEmple(int idEmple) {
    this.idEmple = idEmple;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }
  
}
