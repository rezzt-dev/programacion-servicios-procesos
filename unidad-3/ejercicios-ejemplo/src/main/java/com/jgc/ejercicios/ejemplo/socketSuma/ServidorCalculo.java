/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.ejemplo.socketSuma;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rezzt
 */
public class ServidorCalculo {
  public static void main(String[] args) {
    try {
      ServidorCalculo server = new ServidorCalculo();
      server.escuchar();
    } catch (IOException ex) {
      Logger.getLogger(ServidorCalculo.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void escuchar () throws IOException {
    System.out.println(" > Arrancando el servidor...");
    
    ServerSocket socketEscucha = null;
    Socket conexion = null;
    
    InputStream iStream = null;
    InputStreamReader isReader = null;
    BufferedReader bReader = null;
    
    OutputStream oStream = null;
    PrintWriter pWriter = null;
    
    try {
      socketEscucha = new ServerSocket(9876);
      
      while (true) {
        try {
          conexion = socketEscucha.accept();
          System.out.println("  - Conexion establecida.");
          
          iStream = conexion.getInputStream();
          isReader = new InputStreamReader(iStream);
          bReader = new BufferedReader(isReader);
          
          String simbolo = bReader.readLine();
          String num1 = bReader.readLine();
          String num2 = bReader.readLine();
          
          Integer result = this.calcular(simbolo, num1, num2);
          oStream = conexion.getOutputStream();
          pWriter = new PrintWriter(oStream);
          
          pWriter.write(result.toString() + "\n");
          pWriter.flush();
        } catch (IOException ex) {
          System.out.println("  !Error al cargar la conexion. Problema: " + ex.getMessage());
          ex.printStackTrace();
          throw ex;
        } finally {
          close(pWriter);
          close(oStream);
          close(bReader);
          close(isReader);
          close(iStream);
          close(conexion);
        }
      }
    } catch (IOException ex) {
      System.out.println("  !No se pudo poner el socket a escuchar. Problema: " + ex.getMessage());
      ex.printStackTrace();
      throw ex;
    } finally {
      close(socketEscucha);
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
