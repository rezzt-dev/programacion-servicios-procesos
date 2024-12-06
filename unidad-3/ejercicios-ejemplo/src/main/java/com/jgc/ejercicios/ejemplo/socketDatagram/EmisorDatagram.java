package com.jgc.ejercicios.ejemplo.socketDatagram;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EmisorDatagram {
  public static void main(String[] args) throws IOException {
    try {
      System.out.println("> Emisor Datagram | Creando el socket...");
      DatagramSocket dSocket = new DatagramSocket();

      System.out.println(" - Enviando mensaje...");
      String mensaje = "Mensaje desde el emisor.";

      InetAddress ipAddress = InetAddress.getByName("localhost");
      DatagramPacket dPacket = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, ipAddress, 5555);

      dSocket.send(dPacket);
      System.out.println("  + Mensaje enviado.");

      byte[] buffer = new byte[25];
      DatagramPacket dPacketResponse = new DatagramPacket(buffer, buffer.length);
      dSocket.receive(dPacketResponse);
        System.out.println("  + Respuesta del servidor: " + new String(dPacketResponse.getData()));

      System.out.println("/Cerrando el socket datagram");
      close(dSocket);

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
