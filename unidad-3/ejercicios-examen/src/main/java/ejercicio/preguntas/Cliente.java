/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio.preguntas;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class Cliente {
  public static void main(String[] args) {
    String host = "localhost";
    int puerto = 9876;

    String[] listaPreguntas = {
      "¿cual es la capital de portugal?",
      "¿quien escribio don quijote de la mancha?",
      "¿cual es el rio mas largo del mundo?",
      "¿en que año llego el hombre a la luna?",
      "¿cual es el idioma mas hablado en el mundo?",
      "¿cuantos planetas hay en el sistema solar?" // -> pregunta no reconocida
    };

    for (String auxPregunta : listaPreguntas) {
      try (
        Socket clientSocket = new Socket(host, puerto);
        ObjectOutputStream ooStream = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream oiStream = new ObjectInputStream(clientSocket.getInputStream())    
      ){
        System.out.println("  - Enviado: " + auxPregunta);
        ooStream.writeObject(auxPregunta);

        String response = (String) oiStream.readObject();
        System.out.println("  - Respuesta: " + response);
      } catch (IOException | ClassNotFoundException ex) {
        System.err.println("  - Error al iniciar al cliente.");
      }
    }
  }
}
