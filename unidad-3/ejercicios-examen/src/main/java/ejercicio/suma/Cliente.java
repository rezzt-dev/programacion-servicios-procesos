/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.suma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rezzt
 */
public class Cliente {
  private static void enviarCadena (BufferedReader entrada, PrintWriter salida) {
    try {
      salida.println(2);
      salida.println("ABC");
      salida.println("ZZ");
      salida.flush();
      
      String suma1 = entrada.readLine();
      System.out.println(suma1);
      String suma2 = entrada.readLine();
      System.out.println(suma2);
    } catch (IOException ex) {
      Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void main(String[] args) {
    String host = "localhost";
    int puerto = 9876;

    try (
      Socket socket = new Socket(host, puerto);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
    ) {
      enviarCadena(in, out);
    } catch (IOException ex) {
      Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
