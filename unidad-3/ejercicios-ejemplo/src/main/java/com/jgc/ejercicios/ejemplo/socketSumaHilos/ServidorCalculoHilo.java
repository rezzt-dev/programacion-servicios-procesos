package com.jgc.ejercicios.ejemplo.socketSumaHilos;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCalculoHilo {
  public static void main(String[] args) throws IOException {
    ServerSocket socketEscucha = null;

    try {
      socketEscucha = new ServerSocket(9876);
      System.out.println("> Iniciando el servidor...");

      while (true) {
        try {
          Socket conexion = socketEscucha.accept();
            Peticion hiloPeticion = new Peticion(conexion);
            hiloPeticion.start();

        } catch (IOException ex) {
          ex.printStackTrace();
          throw ex;
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
      throw ex;
    } finally {
      close(socketEscucha);
    }
  }

  private static void close (Closeable socket) {
    try {
      if (null != socket) {
        socket.close();
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
