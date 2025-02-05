/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.numero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author rezzt
 */
public class Cliente {
  private static final String SERVIDOR = "localhost";
  private static final int PUERTO = 9876;

  public static void main(String[] args) {
    try (
      Socket socket = new Socket(SERVIDOR, PUERTO);
      DataInputStream input = new DataInputStream(socket.getInputStream());
      DataOutputStream output = new DataOutputStream(socket.getOutputStream());
      Scanner scanner = new Scanner(System.in)
    ) {
      System.out.println(input.readUTF());

      while (true) {
        System.out.print("Ingresa un número: ");
        int numero = scanner.nextInt();
        output.writeInt(numero);

        String respuesta = input.readUTF();
        System.out.println(respuesta);

        if (respuesta.contains("Felicidades") || respuesta.contains("Fin del juego")) {
          break;
        }
      }
    } catch (Exception e) {}
  }
}
