/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.harrypotter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class Cliente {
  private static final String HOST = "localhost";
  private static final int PUERTO = 9876;

  public static void main(String[] args) {
    for (int i=0; i<12; i++) {
      new Thread(() -> {
        try (
          Socket peticion = new Socket(HOST, PUERTO);
          BufferedReader in = new BufferedReader(new InputStreamReader(peticion.getInputStream()));
        ) {
          System.out.println(" -> Entrando a la tienda de varitas magicas.");
          String respuesta;
          while ((respuesta = in.readLine()) != null) {
            System.out.println("  -> " + respuesta);
          }

        } catch (IOException ex) {
          System.err.println("!ERROR. Se ha producido un error en el cliente");
        }
      }).start();
    }
  }
}
