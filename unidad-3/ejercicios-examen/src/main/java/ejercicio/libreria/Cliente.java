/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.libreria;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class Cliente {
  private static final String SERVER_ADDRESS = "localhost";
  private static final int SERVER_PORT = 9876;

  public static void main(String[] args) {
    try (
      Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
    ) {
      System.out.println("📚 Cliente conectado a la librería.");
      System.out.print("Ingrese el título del libro que desea solicitar: ");
      String bookTitle = userInput.readLine();
      System.out.print("Ingrese la cantidad (entre 1 y 10): ");
      String quantity = userInput.readLine();

      out.println(bookTitle + ";" + quantity);

      String response = in.readLine();
      System.out.println("📩 Respuesta de la librería: " + response);
    } catch (Exception e) {
      System.err.println("❌ Error en el cliente: " + e.getMessage());
    }
  }
}
