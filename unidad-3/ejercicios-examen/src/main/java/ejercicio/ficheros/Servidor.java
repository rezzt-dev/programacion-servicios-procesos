/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.ficheros;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author rezzt
 */
public class Servidor {
  private static final int PUERTO = 1500;
  static final String RUTA_ARCHIVOS = "archivos_servidor/";

  public void iniciarServidor() {
    ExecutorService pool = Executors.newFixedThreadPool(10);

    try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
      System.out.println("Servidor escuchando en el puerto " + PUERTO);

      while (true) {
        Socket clienteSocket = serverSocket.accept();
        pool.execute(new ManejoPeticion(clienteSocket));
      }
    } catch (IOException e) {}
  }
}
