/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.ejemplo.socketSuma;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author rezzt
 */
public class ClienteCalculo {
  public static void main(String[] args) throws Exception {
    Socket socket = null;
    BufferedReader bReader = null;
    PrintWriter pWriter = null;
    InputStreamReader sReader = null;
    
    try {
      InetSocketAddress ipDireccion = new InetSocketAddress("localhost", 9876);
      socket = new Socket();
      socket.connect(ipDireccion);
      
      pWriter = new PrintWriter(socket.getOutputStream());
      pWriter.print("+\n");
      pWriter.print("0\n");
      pWriter.print("84\n");
      
      sReader = new InputStreamReader(socket.getInputStream());
      bReader = new BufferedReader(sReader);
      
      String result = bReader.readLine();
      System.out.println(" > El resultado fue: " + result);
      
    } catch (IOException ex) {
      ex.printStackTrace();
      throw ex;
    } finally {
      close(pWriter);
      close(bReader);
      close(sReader);
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
