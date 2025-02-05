/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.libreria;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author rezzt
 */
public class Servidor {
  private static final int PORT = 9876;
  static final ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();

  public void iniciarServidor () {
    System.out.println("Servidor iniciado en puerto " + PORT);

    inventory.put("Harry Potter y la piedra filosofal", 7);
    inventory.put("El Hombre Iluminado", 15);
    inventory.put("Cien años de soledad", 10);
    inventory.put("Don Quijote de la Mancha", 12);
    inventory.put("1984", 8);
    inventory.put("El Señor de los Anillos", 5);
    inventory.put("Orgullo y Prejuicio", 9);
    inventory.put("El Principito", 10);
    inventory.put("Crimen y Castigo", 6);
    inventory.put("Moby Dick", 8);

    try (ServerSocket server = new ServerSocket(PORT)) {
      while (true) { 
        Socket clientSocket = server.accept();
        new ManejoPeticion(clientSocket).start();
      }
    } catch (Exception e) {
      System.err.println("❌ Error en el servidor: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    new Servidor().iniciarServidor();
  }
}
