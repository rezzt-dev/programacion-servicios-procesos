package ejercicio.harrypotter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejoPeticion extends Thread {
  private final Socket peticionCliente;

  public ManejoPeticion (Socket inputPeticion) {
    this.peticionCliente = inputPeticion;
  }

  @Override
  public void run() {
    try (
      PrintWriter out = new PrintWriter(peticionCliente.getOutputStream())
    ) {
      out.println(" - Bienvenido a la tienda de varitas magicas de Hogwarts.");
      out.flush();
      
      String varitaAsignada;
      synchronized (Servidor.inventarioVaritas) {
        if (!Servidor.inventarioVaritas.isEmpty()) {
          varitaAsignada = Servidor.inventarioVaritas.remove(0);
        } else {
          varitaAsignada = null;
        }
      }
      
      if (varitaAsignada != null) {
        out.println("  -> Se te ha asignado la varita: " + varitaAsignada);
        out.flush();
      } else {
        out.println("  -> Lo sentimos, no hay varitas disponibles en este momento.");
        out.flush();
      }
      
    } catch (IOException ex) {
      System.err.println("!ERROR. Se ha producido un error al manejar la peticion.");
    } finally {
      try {
        peticionCliente.close();
      } catch (IOException ex) {
        System.err.println("!ERROR. No se ha podido cerrar la peticion.");
      }
    }
  }
}
