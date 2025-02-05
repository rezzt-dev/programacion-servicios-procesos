package ejercicio.ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class ManejoPeticion extends Thread {
  private final Socket peticionCliente;

  public ManejoPeticion (Socket inputPeticion) {
    this.peticionCliente = inputPeticion;
  }

  @Override
  public void run() {
    try (
      DataInputStream input = new DataInputStream(peticionCliente.getInputStream());
      DataOutputStream output = new DataOutputStream(peticionCliente.getOutputStream());
    ) {
      String nombreArchivo = input.readUTF();
      File archivo = new File(Servidor.RUTA_ARCHIVOS + nombreArchivo);

      if (archivo.exists()) {
        output.writeUTF("ERROR: Archivo no encontrado.");
        return;
      }

      output.writeUTF("OK");
        try (
          FileInputStream fileInput = new FileInputStream(archivo)
        ) {
          byte[] buffer = new byte[4096];
          int bytesLeidos;
            
          while ((bytesLeidos = fileInput.read(buffer)) != -1) {
            output.write(buffer, 0, bytesLeidos);
          } 
        }
      System.out.println("Archivo enviado: " + nombreArchivo);
    } catch (Exception e) {} finally {
      try {
        peticionCliente.close();  
      } catch (IOException e) {}
    }
  }
}
