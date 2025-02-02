/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author rezzt
 */
public class Cliente {
  private static final String SERVIDOR = "localhost";
  private static final int PUERTO = 1500;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ingrese el nombre del archivo: ");
    String nombreArchivo = scanner.nextLine();

    try (
      Socket socket = new Socket(SERVIDOR, PUERTO);
      DataOutputStream output = new DataOutputStream(socket.getOutputStream());
      DataInputStream input = new DataInputStream(socket.getInputStream())
    ) {
      output.writeUTF(nombreArchivo);
      String respuesta = input.readUTF();

      if (respuesta.equals("ERROR: Archivo no encontrado.")) {
        System.out.println("❌ " + respuesta);
        return;
      }

      File archivoRecibido = new File("archivos_cliente/" + nombreArchivo);
      try (
        FileOutputStream fileOutput = new FileOutputStream(archivoRecibido)
      ) {
        byte[] buffer = new byte[4096];
        int bytesLeidos;
            
        while ((bytesLeidos = input.read(buffer)) != -1) {
          fileOutput.write(buffer, 0, bytesLeidos);
        } 
      }
      System.out.println("✅ Archivo recibido y guardado en: " + archivoRecibido.getAbsolutePath());
    } catch (IOException ex) {} finally {
      try {
        scanner.close();
      } catch (Exception e) {}
    }
  }
}
