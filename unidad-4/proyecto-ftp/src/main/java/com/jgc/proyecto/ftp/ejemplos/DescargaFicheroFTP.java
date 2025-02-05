package com.jgc.proyecto.ftp.ejemplos;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class DescargaFicheroFTP {
  public static void main(String[] args) throws IOException {
    FTPClient clienteFTP = null;
    FileOutputStream outputStream = null;

    try {
      clienteFTP = new FTPClient();
      clienteFTP.connect("localhost", 21);

      if (clienteFTP.login("prueba", "prueba")) {
        System.out.println(" -> conectado al servidor ");

        clienteFTP.enterLocalPassiveMode();
        outputStream = new FileOutputStream("leer.txt");
        boolean done = clienteFTP.retrieveFile("/leer.txt", outputStream);

        if (done) {
          System.out.println(" el archivo se ha descargado correctamente");
        } else {
          System.out.println(" el archivo no se ha descargado");
        }
      } else {
        System.out.println(" -> login fallido");
      }

    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    } finally {
      try {
        if (null != outputStream) {
          outputStream.close();
        }
      } catch (IOException e) {}

      try {
        if (clienteFTP.isConnected()) {
          clienteFTP.logout();
          clienteFTP.disconnect();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
