package com.jgc.ejercicios.ejemplo.socketSumaHilos;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Peticion extends Thread {
  Socket socket;

  public Peticion (Socket inputSocket) {
    socket = inputSocket;
  }

  @Override
  public void run () {
    try {
      escuchar();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private void escuchar () throws IOException {
    InputStream iStream = null;
    InputStreamReader isReader = null;

    BufferedReader bReader = null;
    OutputStream oStream = null;
    PrintWriter pWriter = null;

    try {
      System.out.println(" - Conexion recibida.");
      iStream = socket.getInputStream();
      isReader = new InputStreamReader(iStream);
      bReader = new BufferedReader(isReader);

      String simbolOper = bReader.readLine();
      String num1 = bReader.readLine();
      String num2 = bReader.readLine();

      Integer result = this.calcular (simbolOper, num1, num2);
      oStream = socket.getOutputStream();

      pWriter = new PrintWriter(oStream);
      pWriter.write(result.toString() + "\n");
      pWriter.flush();

    } catch (IOException ex) {
      System.out.println("/Error al aceptar la conexion: " + ex.getMessage());
      ex.printStackTrace();
      throw ex;

    } finally {
      close(pWriter);
      close(oStream);
      close(bReader);
      close(isReader);
      close(iStream);

      try {
        if (null != socket) {
          socket.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  private void close (Closeable socket) {
    try {
      if (null != socket) {
        socket.close();
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private int calcular (String simbolo, String iNum1, String iNum2) {
    int result = 0;
    char oper = simbolo.charAt(0);
    
    int num1 = this.extraerNum(iNum1);
    int num2 = this.extraerNum(iNum2);
    
    switch (oper) {
      case '+' -> {
        result = num1 + num2;
        System.out.println("   + Resultado suma: " + num1 + " + " + num2 + " = " + result);
      }
      
      case '-' -> {
        result = num1 - num2;
        System.out.println("   + Resultado resta: " + num1 + " - " + num2 + " = " + result);
      }
      
      case '*' -> {
        result = num1 * num2;
        System.out.println("   + Resultado multiplicacion: " + num1 + " * " + num2 + " = " + result);
      }
      
      case '/' -> {
        result = num1 / num2;
        System.out.println("   + Resultado division: " + num1 + " / " + num2 + " = " + result);
      }
    }
    
    return result;
  }
  
  private int extraerNum (String inputLine) {
    int numero;
    
    try {
      numero = Integer.parseInt(inputLine);
    } catch (NumberFormatException ex) {
      numero = 0;
    }
    
    if (numero >= 10000000) {
      numero = 0;
    }
    
    return numero;
  }
}
