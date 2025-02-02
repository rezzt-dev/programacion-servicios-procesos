package ejercicio.suma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejoPeticion extends Thread {
  private final Socket peticionCliente;

  public ManejoPeticion (Socket inputPeticion) {
    this.peticionCliente = inputPeticion;
  }

  private int calcularSuma (String inputLine) {
    int result = 0;
    for (char c : inputLine.toCharArray()) {
      result += (int) c;
    }
    
    return result;
  }

  @Override
  public void run() {
    try (
      BufferedReader in = new BufferedReader(new InputStreamReader(peticionCliente.getInputStream()));
      PrintWriter out = new PrintWriter(peticionCliente.getOutputStream(), true)
    ) {
      String mensaje = in.readLine();
      int numLines = Integer.parseInt(mensaje);

      for (int i=0; i < numLines; i++) {
        String palabra = in.readLine();
        int checksuma = calcularSuma(palabra);
        out.println(checksuma);
      }

    } catch (Exception e) {
      System.out.println(" -> Error al procesar peticion");
    } finally {
      try {
        peticionCliente.close();
      } catch (IOException ex) {
        System.err.println("  - Error cerrando el socket");
      }
    }
  }
}
