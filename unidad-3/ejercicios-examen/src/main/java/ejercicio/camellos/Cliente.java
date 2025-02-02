package ejercicio.camellos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
  public static void main(String[] args) {
    String hostname = "localhost";
    int puerto = 9876;
        
    try (
      Socket socket = new Socket(hostname, puerto);
      ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
      Scanner scanner = new Scanner(System.in)
    ) {

    System.out.println("Conectado al servidor. Esperando la carrera...");
            
    while (true) {
      String mensaje = (String) input.readObject();
      System.out.println(mensaje);

      if (mensaje.contains("Tu turno")) {
        System.out.println("Presiona Enter para lanzar los dados...");
        scanner.nextLine();
        output.writeObject("tirar");
      }

      if (mensaje.contains("ha ganado la carrera")) {
        break;
      }
    }
    
    } catch (IOException | ClassNotFoundException e) {}
  }
}
