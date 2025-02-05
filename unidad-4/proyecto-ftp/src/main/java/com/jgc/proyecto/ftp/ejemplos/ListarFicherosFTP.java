package com.jgc.proyecto.ftp.ejemplos;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ListarFicherosFTP {
  public static void main(String[] args) throws IOException {
    FTPClient clienteFTP = null;

    try {
      clienteFTP = new FTPClient();
      clienteFTP.connect("localhost", 21);

      if (clienteFTP.login("prueba", "prueba")) {
        System.out.println(" -> conectado al servidor ftp");
        clienteFTP.enterLocalPassiveMode();

        FTPFile[] listFiles = clienteFTP.listFiles("/");
        for (FTPFile aux : listFiles) {
          System.out.println(" -> Filename: " + aux.getName());
        }
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
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
