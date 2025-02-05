/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.proyecto.ftp.ejemplos;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * @version 1.0
 * Created on 30 ene 2025
 */
public class LoginBasico {
  public static void main(String[] args) throws IOException {
    FTPClient clienteFTP = null;
    
    try {
       // establecer una nueva conexion con el servidor FTP ->
      clienteFTP = new FTPClient();
      clienteFTP.connect("localhost", 21);
      if (clienteFTP.login("prueba", "prueba")) {
        System.out.println(" -> conectado al servidor FTP");
        
         // usar el modo pasivo ->
        clienteFTP.enterLocalActiveMode();
      } else {
        System.out.println(" -> login fallido");
      }
    } catch (IOException ex) {
      ex.printStackTrace();
      throw ex;
    } finally {
      try {
        if (clienteFTP.isConnected()) {
          clienteFTP.logout();
          clienteFTP.disconnect();
        }
      } catch (IOException ex) {}
    }
  }
}
