package com.jgc.ejercicios.examen.ejerciciosRefrescos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejoPeticion extends Thread {
  private final Socket peticionCliente;
  private static final Object candado = new Object();

  public ManejoPeticion (Socket inputPeticion) {
    this.peticionCliente = inputPeticion;
  }

  @Override
  public void run () {
    try (
      BufferedReader in = new BufferedReader(new InputStreamReader(peticionCliente.getInputStream()));
      PrintWriter out = new PrintWriter(peticionCliente.getOutputStream())
    ) {
      String mensaje = in.readLine();
      int bebidasPedidas = Integer.parseInt(mensaje);

      int bebidasSacadas;
      synchronized (candado) {
        if (Servidor.bebidasTotales >= bebidasPedidas) {
          bebidasSacadas = bebidasPedidas;
          Servidor.bebidasTotales -= bebidasPedidas;
        } else {
          bebidasSacadas = Servidor.bebidasTotales;
          Servidor.bebidasTotales = 0;
          out.println("  -> No hay suficientes refrescos");
          out.flush();
        }
      }

      out.println("  -> Se han dispensado " + bebidasSacadas + " refrescos");
      out.flush();

      if (Servidor.bebidasTotales == 0) {
        out.println(" -> La maquina se ha quedado vacia");
        out.flush();
      }
    } catch (Exception e) {
      System.err.println("  -> Error en el cliente: " + e.getMessage());
    }
  }
}
