/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.maquinaRefrescos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class ManejoPeticion extends Thread {
   // constantes & atributos ->
  private Socket peticionCliente;
  private static final Object candado = new Object();
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // constructores ->
  public ManejoPeticion (Socket inputPeticion) {
    this.peticionCliente = inputPeticion;
  }
  
 //---------------------------------------------------------------------------------------------------------------------------->
   // metodos publicos ->
  @Override
  public void run () {
    try (BufferedReader in = new BufferedReader(new InputStreamReader(peticionCliente.getInputStream()));
            PrintWriter out = new PrintWriter(peticionCliente.getOutputStream())) {
      
      String peticion = in.readLine();
      int bebidasPedidas = Integer.parseInt(peticion);
      
      int bebidasSacadas;
      synchronized (candado) {
        if (MaquinaRefrescos.bebidasTotales >= bebidasPedidas) {
          bebidasSacadas = bebidasPedidas;
          MaquinaRefrescos.bebidasTotales -= bebidasPedidas;
        } else {
          bebidasSacadas = MaquinaRefrescos.bebidasTotales;
          MaquinaRefrescos.bebidasTotales = 0;
          out.println("  - No hay suficientes refrescos");
          out.flush();
        }
      }
      
      out.println("  - Se han dispensado " + bebidasSacadas + " refrescos");
      out.flush();
      
      if (MaquinaRefrescos.bebidasTotales == 0) {
        out.println(" -> La maquina se ha quedado vacia");
        out.flush();
      }
      
    } catch (IOException ex) {
      System.err.println(" -> Ha habido un problema al manejar la peticion.");
    }
  }
}
