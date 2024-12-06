package com.jgc.ejercicios.ejemplo.socketSumaHilos;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteCalculo {
  public static void main(String[] args) throws IOException {
    Socket socket = null;
    BufferedReader bReader = null;
    PrintWriter pWriter = null;
    InputStreamReader isReader = null;

    try {
      InetSocketAddress ipAddress = new InetSocketAddress("localhost", 5555);
      socket = new Socket();
      socket.connect(ipAddress);

      pWriter = new PrintWriter(socket.getOutputStream());
      pWriter.print("+\n");
      pWriter.print("0\n");
      pWriter.print("84\n");
      pWriter.flush();

      isReader = new InputStreamReader(socket.getInputStream());
      bReader = new BufferedReader(isReader);

      String result = bReader.readLine();
      System.out.println("  + El resultado fue: " + result);

    } catch (IOException ex) {
      ex.printStackTrace();
      throw ex;

    } finally {
      close(pWriter);
      close(bReader);
      close(isReader);
      close(socket);
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
