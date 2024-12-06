package com.jgc.ejercicios.ejemplo.socketStream;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteStream {
  public static void main(String[] args) throws IOException {
    System.out.println("> Creando el Socket Stream.");
    Socket socket = new Socket();
    System.out.println(" - Conexion establecida.");

    InetSocketAddress ipAddress = new InetSocketAddress("localhost", 5555);
    socket.connect(ipAddress);

    InputStream iStream = socket.getInputStream();
    OutputStream oStream = socket.getOutputStream();

    System.out.println("  + Enviando mensaje.");
    String mensaje = "Hola!!\n";

    oStream.write(mensaje.getBytes());
    System.out.println("  + Mensaje enviado.");

    System.out.println("/Cerrando el socket datagram");
    close(socket);

    System.out.println(" - Conexion finalizada.");
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
