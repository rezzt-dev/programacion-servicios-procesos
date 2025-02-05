package com.jgc.proyecto.ftp.ejemplos;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class SubidaFicheroFTP {
  public static void main(String[] args) throws IOException {
    FTPClient clienteFTP = null;
    FileInputStream inputStream = null;
    
    try {
       // establecer una nueva conexion con el servidor FTP ->
      clienteFTP = new FTPClient();
      clienteFTP.connect("localhost", 21);
      if (clienteFTP.login("prueba", "prueba")) {
        System.out.println(" -> conectado al servidor FTP");
        
         // usar el modo pasivo ->
        clienteFTP.enterLocalActiveMode();
        clienteFTP.setFileType(FTP.ASCII_FILE_TYPE);
        inputStream = new FileInputStream("prueba.txt");
        boolean done = clienteFTP.storeFile("/prueba.txt", inputStream);

        if (done) {
          System.out.println(" > el archivo se ha enviado correctamente.");
        } else {
          System.out.println("el archivo no se ha enviado");
        }
      } else {
        System.out.println(" -> login fallido");
      }
    } catch (IOException ex) {
      ex.printStackTrace();
      throw ex;
    } finally {
      try {
        if (null != inputStream) {
          inputStream.close();
        }
      } catch(IOException ex) {}
      try {
        if (clienteFTP.isConnected()) {
          clienteFTP.logout();
          clienteFTP.disconnect();
        }
      } catch (IOException ex) {}
    }
  }
}
