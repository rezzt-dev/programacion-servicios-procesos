/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.psp.ejercicio1;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;



/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 19 sept 2024
 */
public class LanzadorMultiplicador {
  public static void lanzarMultiplicador (int num1, int num2, String outputFile) throws IOException, InterruptedException {
    ProcessBuilder pb = new ProcessBuilder ("java", "-cp", ".", "com.psp.Multiplicador", String.valueOf(num1), String.valueOf(num2));
    pb.redirectError(new File("files" + File.separator + "error-" + System.currentTimeMillis() + ".log"));
    pb.redirectError(new File("files" + File.separator + outputFile));
    
    Process proceso = pb.start();
    proceso.waitFor(10, TimeUnit.SECONDS);
    
    if (proceso.exitValue() == 0) {
      System.out.println("Proceso completado exitosamente. Archivo de salida: " + outputFile);
      System.out.println("Resultado guardado en output file: ");
      System.out.println(new String(java.nio.file.Files.readAllBytes(new File("files/" + outputFile).toPath())));
    } else {
      System.out.println("Proceso fallido: " + proceso.exitValue());
    }
  }
  
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Operacion: java LanzadorMultiplicador <num1> <num2> <outputFile>");
      return;
    }
    
    int num1 = Integer.parseInt(args[0]);
    int num2 = Integer.parseInt(args[1]);
    String outputFile = args[2];
    
    try {
      lanzarMultiplicador(num1, num2, outputFile);
    } catch (IOException | InterruptedException e) {
      System.out.println("Error al lanzar el proceso! " + e.getMessage());
    }
  }
}
