package com.jgc.ejercicios.multihilos.contestarPreguntas;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ManageCliente extends Thread {
  private Socket clientSocket;

  public ManageCliente (Socket inputSocket) {
    this.clientSocket = inputSocket;
  }

  @Override
  public void run () {
    try (ObjectInputStream oiStream = new ObjectInputStream(clientSocket.getInputStream());
         ObjectOutputStream ooStream = new ObjectOutputStream(clientSocket.getOutputStream())
    ) {
      String pregunta = (String) oiStream.readObject();
      String respuesta = responderPregunta(pregunta);

      ooStream.writeObject(respuesta);

    } catch (IOException | ClassNotFoundException ex) {
      ex.printStackTrace();
    } finally {
      try {
        clientSocket.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  private String responderPregunta (String inputPregunta) {
    String returnResponse = "";

    switch (inputPregunta.toLowerCase()) {
      case "¿cual es la capital de portugal?":
        returnResponse = "lisboa";
        break;

      case "¿quien es don quijote de la mancha?":
        returnResponse = "miguel de cervantes";  
        break;

      case "¿cual es el rio mas largo del mundo?":
        returnResponse = "amazonas";
        break;

      case "¿en que año llego el hombre a la luna?":
        returnResponse = "1969";
        break;

      case "¿cual es el idioma mas hablado del mundo?":
        returnResponse = "chino mandarin";
        break;
      
      default:
        returnResponse = "no entiendo la pregunta";
        break;
    }

    return returnResponse;
  }
}
