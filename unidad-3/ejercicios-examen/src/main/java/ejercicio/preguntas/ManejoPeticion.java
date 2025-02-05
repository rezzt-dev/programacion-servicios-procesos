package ejercicio.preguntas;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ManejoPeticion extends Thread {
  private final Socket peticionCliente;

  public ManejoPeticion (Socket inputPeticion) {
    this.peticionCliente = inputPeticion;
  }

  private String responderPregunta (String inputPregunta) {
    String returnResponse = switch (inputPregunta.toLowerCase()) {
          case "¿cual es la capital de portugal?" -> "lisboa";
          case "¿quien escribio don quijote de la mancha?" -> "miguel de cervantes";
          case "¿cual es el rio mas largo del mundo?" -> "amazonas";
          case "¿en que año llego el hombre a la luna?" -> "1969";
          case "¿cual es el idioma mas hablado del mundo?" -> "chino mandarin";
          default -> "no entiendo la pregunta";
      };

    return returnResponse;
  }

  @Override
  public void run() {
    try (
      ObjectInputStream oiStream = new ObjectInputStream(peticionCliente.getInputStream());
      ObjectOutputStream ooStream = new ObjectOutputStream(peticionCliente.getOutputStream())
    ) {
      String pregunta = (String) oiStream.readObject();
      String respuesta = responderPregunta(pregunta);

      ooStream.writeObject(respuesta);
    } catch (IOException | ClassNotFoundException ex) {} finally {
      try {
        peticionCliente.close();
      } catch (IOException ex) {}
    }
  }
}
