package ejercicio.libreria;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejoPeticion extends Thread {
  private final Socket peticionCliente;

  public ManejoPeticion (Socket peticionCliente) {
    this.peticionCliente = peticionCliente;
  }

  @Override
  public void run () {
    try (
      BufferedReader in = new BufferedReader(new InputStreamReader(peticionCliente.getInputStream()));
      PrintWriter out = new PrintWriter(peticionCliente.getOutputStream(), true)
    ) {
      String request = in.readLine();
      System.out.println("📩 Solicitud recibida: " + request);

      String[] parts = request.split(";");
      if (parts.length != 2) {
        out.println("❌ Formato de solicitud incorrecto. Use: Titulo;Cantidad");
        return;
      }

      String bookTitle = parts[0].trim();
      int requestedQuantity;

      try {
        requestedQuantity = Integer.parseInt(parts[1].trim());
        if (requestedQuantity < 1 || requestedQuantity > 10) {
          out.println("❌ La cantidad debe estar entre 1 y 10.");
          return;
        }
      } catch (NumberFormatException e) {
        out.println("❌ Cantidad no válida.");
        return;
      }

      synchronized (Servidor.inventory) {
        if (Servidor.inventory.containsKey(bookTitle)) {
          int availableStock = Servidor.inventory.get(bookTitle);
          if (availableStock > 0) {
            int providedQuantity = Math.min(requestedQuantity, availableStock);
            Servidor.inventory.put(bookTitle, availableStock - providedQuantity);
            out.println("✅ Se han enviado " + providedQuantity + " unidades de '" + bookTitle + "'.");
            System.out.println("📦 Enviado " + providedQuantity + " de '" + bookTitle + "'. Quedan: " + Servidor.inventory.get(bookTitle));
          } else {
            out.println("⚠️ No quedan ejemplares de '" + bookTitle + "' en stock.");
            System.out.println("❌ Stock agotado para '" + bookTitle + "'.");
          }
        } else {
          out.println("⚠️ El libro '" + bookTitle + "' no está disponible en la librería.");
          System.out.println("❌ Libro no encontrado: " + bookTitle);
        }
      }
    } catch (Exception e) {}
  }
}
