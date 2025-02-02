package ejercicio.numero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ManejadorPeticion extends Thread {
  private final Socket peticionCliente;

  public ManejadorPeticion (Socket inputPeticion) {
    this.peticionCliente = inputPeticion;
  }

  private void cerrarConexion() {
    try {
      try (peticionCliente) {
        synchronized (Servidor.clientes) {
          Servidor.clientes.remove(peticionCliente);
        }
      }
    } catch (IOException e) {}
  }

  @Override
  public void run() {
    try (
      DataInputStream input = new DataInputStream(peticionCliente.getInputStream());
      DataOutputStream output = new DataOutputStream(peticionCliente.getOutputStream());
    ) {
      output.writeUTF("Bienvenido! Adivina un número entre 0 y 100.");

      while (!Servidor.numeroAdivinado) {
        int intento = input.readInt();
        if (intento == Servidor.NUMERO_SECRETO) {
          Servidor.numeroAdivinado = true;
          output.writeUTF("¡Felicidades! Has adivinado el número.");
          Servidor.notificarClientes("El número secreto ha sido adivinado por alguien. Fin del juego.");
          break;
        } else if (intento < Servidor.NUMERO_SECRETO) {
          output.writeUTF("El número es mayor.");
        } else {
          output.writeUTF("El número es menor.");
        }
      }
    } catch (Exception e) {} finally {
      cerrarConexion();
    }
  }
}
