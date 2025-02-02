/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.harrypotter;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author rezzt
 */
public class Servidor {
  static final List<String> inventarioVaritas = Collections.synchronizedList(new ArrayList<>(Arrays.asList(
          "Varita de Saúco",
          "Varita de Acebo y Pluma de Fénix",
          "Varita de Vid y Pelo de Unicornio",
          "Varita de Fresno y Nervio de Dragón",
          "Varita de Cerezo y Pluma de Fénix",
          "Varita de Nogal y Pelo de Thestral",
          "Varita de Abeto y Pelo de Unicornio",
          "Varita de Sauce y Nervio de Dragón",
          "Varita de Espino y Pluma de Fénix",
          "Varita de Tejo y Pluma de Fénix"
  )));
  private static final int PUERTO = 9876;

  private void iniciarServidor() {
    try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
      System.out.println(" -> Servidor iniciado en el puerto " + PUERTO);
      while (true) { 
        Socket peticionCliente = serverSocket.accept();
        System.out.println(" -> Peticion recibida desde " + peticionCliente.getInetAddress());
        new Thread(new ManejoPeticion(peticionCliente)).start();
      }
    } catch (Exception e) {
      System.out.println(" -> Error al iniciar el servidor");
    }
  }

  public static void main(String[] args) {
    new Servidor().iniciarServidor();
  }
}
