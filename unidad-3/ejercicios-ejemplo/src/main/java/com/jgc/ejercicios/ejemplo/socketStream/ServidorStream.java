package com.jgc.ejercicios.ejemplo.socketStream;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorStream {
  public static void main(String[] args) throws IOException {
    try {
      System.out.println("> Creando el Server Socket Stream.");
      ServerSocket socket = new ServerSocket();
      System.out.println(" - Realizando conexion...");

      InetSocketAddress ipAddress = new InetSocketAddress("localhost", 5555);
      socket.bind(ipAddress);
      System.out.println("  + Aceptando solicitudes.");

      Socket newSocket = socket.accept();
      System.out.println(" - Conexion establecida.");

      InputStream iStream = newSocket.getInputStream();
      OutputStream oStream = newSocket.getOutputStream();

      byte[] mensaje = new byte[25];
      iStream.read(mensaje);
        System.out.println("  + Mensaje recibido: " + new String(mensaje));

      System.out.println("/Cerrando el socket");
      close(newSocket);

      System.out.println("/Cerrando el server socket stream");
      close(socket);

      System.out.println(" - Conexion finalizada.");

    } catch (IOException ex) {
      ex.printStackTrace();
      throw ex;
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
