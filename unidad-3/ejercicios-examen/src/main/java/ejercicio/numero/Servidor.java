/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.numero;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rezzt
 */
public class Servidor {
  private static final int PUERTO = 9876;
  static final int NUMERO_SECRETO = new Random().nextInt(101);
  static final List<Socket> clientes = Collections.synchronizedList(new ArrayList<>());
  static boolean numeroAdivinado = false;

  public static void main(String[] args) {
    System.out.println("Servidor iniciado. Número secreto generado: " + NUMERO_SECRETO);

    try (
      ServerSocket serverSocket = new ServerSocket(PUERTO)
    ) {
      while (!numeroAdivinado) {
        Socket clienteSocket = serverSocket.accept();

        synchronized (clientes) {
          clientes.add(clienteSocket);
        }

        new Thread(new ManejadorPeticion(clienteSocket)).start();
    }
    } catch (IOException e) {}
  }


  static void notificarClientes(String mensaje) {
    synchronized (clientes) {
      for (Socket cliente : clientes) {
        try {
          DataOutputStream output = new DataOutputStream(cliente.getOutputStream());
          output.writeUTF(mensaje);
          cliente.close();
        } catch (IOException e) {}
      }

      clientes.clear();
    }
  }
}
