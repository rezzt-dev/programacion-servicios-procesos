package com.jgc;

import java.io.File;

public class Lanzador {
  public void lanzadorSumador (int n1, int n2) {
    String clase = "com.jgc.Sumador";
    ProcessBuilder pb;
    Process process = null;

    try {
      pb = new ProcessBuilder("java", clase, String.valueOf(n1), String.valueOf(n2));
      pb.redirectError(new File("files" + File.separator + "error.log"));
      process = pb.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Lanzador launcher = new Lanzador();
    launcher.lanzadorSumador(1, 21);
    launcher.lanzadorSumador(30, 100);
  }
}
