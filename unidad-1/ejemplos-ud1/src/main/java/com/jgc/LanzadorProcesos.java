package com.jgc;

import java.io.IOException;

public class LanzadorProcesos {
  public void ejecutar (String ruta) {
    ProcessBuilder pb;

    try {
      pb = new ProcessBuilder(ruta);
      pb.start();
    } catch (IOException e) {
    }
  }

  public static void main(String[] args) {
    String ruta = "C:\\Program Files\\Adobe\\Acrobat DC\\Acrobat\\Acrobat.exe";
    LanzadorProcesos lp = new LanzadorProcesos();

    lp.ejecutar(ruta);
    System.out.println(" > Finalizado.");
  }
}
