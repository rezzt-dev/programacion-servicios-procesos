package com.jgc.ejercicios.examen.ejerciciosManuales;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {
  private static final int PORT = 9876;
  static final Map<String, Integer> inventario = new HashMap<>();

  private void introducirDatosInventario () {
    inventario.put("Harry Potter y la piedra filosofal", 7);
    inventario.put("El Hombre Iluminado", 15);
    inventario.put("Cien años de soledad", 10);
    inventario.put("Don Quijote de la Mancha", 12);
    inventario.put("1984", 8);
    inventario.put("El Señor de los Anillos", 5);
    inventario.put("Orgullo y Prejuicio", 9);
    inventario.put("El Principito", 10);
    inventario.put("Crimen y Castigo", 6);
    inventario.put("Moby Dick", 8);
  }

  public void iniciarServidor() {
    System.out.println(" -> Servidor iniciado en el puerto " + PORT);
    introducirDatosInventario();
    
    try (ServerSocket server = new ServerSocket(PORT)) {
      while (true) { 
        Socket peticionCliente = server.accept();
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
