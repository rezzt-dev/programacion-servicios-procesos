package ejercicio.camellos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Servidor {
  private static final int PUERTO = 9876;
  private static final int NUM_JUGADORES = 5;
  private static final int META = 100;
  private static final Random random = new Random();

  private static final List<Socket> jugadores = new ArrayList<>();
  private static final Map<Socket, Integer> posiciones = new HashMap<>();
  private static int turnoActual = 0;
  @SuppressWarnings("unused")
  private static boolean carreraIniciada = false;

  public static void main(String[] args) {
    try (
      ServerSocket serverSocket = new ServerSocket(PUERTO)
    ) {
      System.out.println("Servidor esperando jugadores...");

      while (jugadores.size() < NUM_JUGADORES) {
        Socket socket = serverSocket.accept();
        jugadores.add(socket);
        posiciones.put(socket, 0);
        System.out.println("Jugador " + jugadores.size() + " conectado desde " + socket.getInetAddress());
      }

      System.out.println("¡Todos los jugadores conectados! La carrera va a comenzar...");
      carreraIniciada = true;
      enviarMensajeATodos("🏁 ¡La carrera ha comenzado! 🏁");
      enviarMensajeATodos("Turno del Jugador 1");

      while (true) {
        Socket jugadorActual = jugadores.get(turnoActual);
        try {
          ObjectOutputStream output = new ObjectOutputStream(jugadorActual.getOutputStream());
          output.writeObject("Tu turno. Lanza los dados para avanzar.");
                    
          ObjectInputStream input = new ObjectInputStream(jugadorActual.getInputStream());
          input.readObject(); // Esperamos confirmación del jugador
                    
          int avance = random.nextInt(10) + 1;
          posiciones.put(jugadorActual, posiciones.get(jugadorActual) + avance);
          enviarMensajeATodos("Jugador " + (turnoActual + 1) + " avanzó " + avance + " metros. (Total: " + posiciones.get(jugadorActual) + "m)");

          if (posiciones.get(jugadorActual) >= META) {
            enviarMensajeATodos("🏆 Jugador " + (turnoActual + 1) + " ha ganado la carrera! 🏆");
            break;
          }

          turnoActual = (turnoActual + 1) % NUM_JUGADORES;
          enviarMensajeATodos("Turno del Jugador " + (turnoActual + 1));

        } catch (IOException | ClassNotFoundException e) {
          break;
        }
      }
    } catch (IOException e) {}
  }

  private static void enviarMensajeATodos(String mensaje) {
    for (Socket jugador : jugadores) {
      try {
        ObjectOutputStream output = new ObjectOutputStream(jugador.getOutputStream());
        output.writeObject(mensaje);
      } catch (IOException e) {}
    }
  }
}
