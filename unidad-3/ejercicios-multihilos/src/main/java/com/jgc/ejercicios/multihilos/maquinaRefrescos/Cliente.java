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
import java.util.Random;

/**
 *
 * @author rezzt
 */
public class Cliente {
  private static final String HOST = "localhost";
  private static final int PUERTO = 9876;
  
  public static void main(String[] args) {
    for (int i=0; i<20; i++) {
      new Thread(() -> {
        try (Socket peticion = new Socket(HOST, PUERTO);
              BufferedReader in = new BufferedReader(new InputStreamReader(peticion.getInputStream()));
              PrintWriter out = new PrintWriter(peticion.getOutputStream())) {

          int bebidasPedidas = new Random().nextInt(11);
          out.println(bebidasPedidas);
          out.flush();

          String respuesta = in.readLine();
          System.out.println("  -> Cliente recibio: " + respuesta);

        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }).start();
    }
  }
}
