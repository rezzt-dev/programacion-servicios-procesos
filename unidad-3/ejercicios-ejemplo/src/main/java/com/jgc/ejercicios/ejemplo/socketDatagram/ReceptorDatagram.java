package com.jgc.ejercicios.ejemplo.socketDatagram;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ReceptorDatagram {
  public static void main(String[] args) throws IOException {
    System.out.println("> Creando el Socket Datagram.");

    InetSocketAddress ipAddress = new InetSocketAddress("localhost", 5555);
    DatagramSocket dSocket = new DatagramSocket(ipAddress);

    System.out.println(" - Conexion establecida.");
    byte[] mensaje = new byte[25];
    DatagramPacket dPacket = new DatagramPacket(mensaje, 25);
    dSocket.receive(dPacket);

    System.out.println("  + Mensaje recibido: " + new String(mensaje));
    System.out.println("  + Enviando mensaje.");

    String respuesta = "Hola desde el servidor!";
      byte[] buffer = respuesta.getBytes();
      DatagramPacket dPacketResponse = new DatagramPacket(buffer, buffer.length, dPacket.getAddress(), dPacket.getPort());
      dSocket.send(dPacketResponse);
    
    System.out.println("  + Mensaje enviado.");

    System.out.println("/Cerrando el socket datagram");
    close(dSocket);

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
