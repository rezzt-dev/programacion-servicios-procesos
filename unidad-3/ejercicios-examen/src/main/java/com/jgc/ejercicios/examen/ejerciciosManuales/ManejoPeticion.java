package com.jgc.ejercicios.examen.ejerciciosManuales;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejoPeticion extends Thread {
  private final Socket peticionCliente;

  public ManejoPeticion (Socket peticionCliente) {
    this.peticionCliente = peticionCliente;
  }

  @Override
  public void run () {
    try (
      BufferedReader in = new BufferedReader(new InputStreamReader(peticionCliente.getInputStream()));
      PrintWriter out = new PrintWriter(peticionCliente.getOutputStream(), true)
    ) {
      String respuesta = in.readLine();
      System.out.println(" <- Solicitud recibida: " + respuesta);

      String[] partes = respuesta.split(";");
      if (partes.length != 2) {
        System.out.println(" <- Formato de solicitud incorrecto. Use: Titulo;Cantidad");
        return;
      }

      String tituloLibro = partes[0].trim();
      int cantidadRespuesta;

      try {
        cantidadRespuesta = Integer.parseInt(partes[1].trim());

        if (cantidadRespuesta < 1 || cantidadRespuesta > 10) {
          out.println(" <- La cantidad debe estar entre 1 y 10.");
          return;
        }
      } catch (NumberFormatException e) {
        out.println(" <- Error al procesar la solicitud.");
        return;
      }

      synchronized (Servidor.inventario) {
        if (Servidor.inventario.containsKey(tituloLibro)) {
          int stockDisponible = Servidor.inventario.get(tituloLibro);
          if (stockDisponible > 0) {
            int cantidadDada = Math.min(cantidadRespuesta, stockDisponible);
            Servidor.inventario.put(tituloLibro, stockDisponible - cantidadDada);
            
            out.println(" <- Se han enviado " + cantidadDada + " unidades de '" + tituloLibro + "'.");
            System.out.println(" -> Enviado " + cantidadDada + " de '" + tituloLibro + "'. Quedan: " + Servidor.inventario.get(tituloLibro));
          } else {
            out.println(" <- No quedan ejemplares de '" + tituloLibro + "' en stock.");
            System.out.println(" -> Stock agotado para '" + tituloLibro + "'.");
          }
        }
      }
    } catch (Exception e) {}
  }
}
