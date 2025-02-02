package com.jgc.ejercicios.examen.ejerciciosManuales;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
  private static final String NAME = "localhost";
  private static final int PORT = 9876;

  public static void main(String[] args) {
    try (
      Socket socket = new Socket(NAME, PORT);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
    ) {
      System.out.println(" -> Cliente conectado a la librería.");

      System.out.println("  => Ingrese el título del libro que desea solicitar: ");
      String tituloLibro = userInput.readLine();

      System.out.println("  => Ingrese la cantidad (entre 1 y 10): ");
      String cantidad = userInput.readLine();

      out.println(tituloLibro + ";" + cantidad);
      String respuesta = in.readLine();
      System.out.println(" <- Respuesta de la librería: " + respuesta);
    } catch (Exception e) {
      System.err.println(" -> Error en el cliente: " + e.getMessage());
    }
  }
}
