/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.ejemploObserver;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rezzt
 */
public class Avion {
   // constantes & variables ->
  private int altura;
  private int velocidad;
  private String direccion;
  private List<AvionObserver> observadores = new ArrayList<>();
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // constructores ->
  public Avion (int inputAltura, int inputVelocidad, String inputDireccion) {
    this.altura = inputAltura;
    this.velocidad = inputVelocidad;
    this.direccion = inputDireccion;
  }
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // metodos publicos ->
  public void agregarObservador (AvionObserver inputObserver) {
    this.observadores.add(inputObserver);
  }
  
  public void eliminarObservador (AvionObserver inputObserver) {
    this.observadores.remove(inputObserver);
  }
  
  public void notificarObservadores () {
    for (AvionObserver aux : observadores) {
      aux.actualizar(altura, velocidad, direccion);
    }
  }
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // metodos publicos ->
  public void subir () {
    altura += 100;
    System.out.println(" -> El avion sube a una altura de " + altura + "m.");
    notificarObservadores();
  }
  
  public void bajar () {
    altura -= 100;
    System.out.println(" -> El avion baja a una altura de " + altura + "m.");
    notificarObservadores();
  }
  
  public void acelerar () {
    velocidad += 200;
    System.out.println(" -> El avion acelera a una velocidad de " + velocidad + " k/h.");
    notificarObservadores();
  }
  
  public void frenar () {
    velocidad -= 200;
    System.out.println(" -> El avion reduce a una velocidad de " + velocidad + " k/h.");
    notificarObservadores();
  }
  
  public void girar (String inputDireccion) {
    direccion = inputDireccion;
    System.out.println(" -> El avion gira hacia la " + direccion + ".");
    notificarObservadores();
  }
  
  public void mostrarEstado () {
    System.out.println(" -> Estado del avion: altura-" + altura + " | velocidad-" + velocidad + " | direccion-" + direccion + ".");
  }
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // getters & setters ->
  public int getAltura() {
    return altura;
  }

  public void setAltura(int altura) {
    this.altura = altura;
  }

  public int getVelocidad() {
    return velocidad;
  }

  public void setVelocidad(int velocidad) {
    this.velocidad = velocidad;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public List<AvionObserver> getObservadores() {
    return observadores;
  }

  public void setObservadores(List<AvionObserver> observadores) {
    this.observadores = observadores;
  }
  
}
