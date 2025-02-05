package com.jgc.ejercicios.examen.ejerciciosRefrescos;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Cliente {
  private static final String HOST = "localhost";
  private static final int PUERTO = 9876;

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      new Thread(() -> {
        try (
          Socket peticion = new Socket(HOST, PUERTO);
          BufferedReader in = new BufferedReader(new java.io.InputStreamReader(peticion.getInputStream()));
          PrintWriter out = new PrintWriter(peticion.getOutputStream())
        ) {
          int bebidasPedidas = new Random().nextInt(11);
          out.println(bebidasPedidas);
          out.flush();

          String respuesta = in.readLine();
          System.out.println(" -> Cliente recibio: " + respuesta);
        } catch (Exception e) {
        }
      }).start();
    }
  }
}
