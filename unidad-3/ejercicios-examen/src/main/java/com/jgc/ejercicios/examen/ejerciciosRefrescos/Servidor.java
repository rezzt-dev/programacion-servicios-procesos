package com.jgc.ejercicios.examen.ejerciciosRefrescos;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
  private static final int PORT = 9876;
  static int bebidasTotales = 0;

  private void iniciarServidor () {
    try (
      ServerSocket server = new ServerSocket(PORT)
    ) {
      System.out.println(" -> Servidor iniciado en el puerto " + PORT);

      while (true) { 
        Socket peticionCliente = server.accept();
        System.out.println("  -> Petición recibida");
        new ManejoPeticion(peticionCliente).start();
      }
    } catch (Exception e) {
      System.err.println(" -> Error en el servidor: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    new Servidor().iniciarServidor();
  }
}
