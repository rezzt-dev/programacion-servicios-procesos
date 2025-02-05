/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.refrescos;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class Servidor {
  private static final int PUERTO = 9876;
  static int bebidasTotales = 100;

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
