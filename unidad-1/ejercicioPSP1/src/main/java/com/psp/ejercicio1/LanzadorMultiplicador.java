/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.psp.ejercicio1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;



/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 19 sept 2024
 */
public class LanzadorMultiplicador {
  public static void lanzarMultiplicador(List<String> params, String outputFile) throws IOException, InterruptedException {
    String clase = "com.psp.ejercicio1.Multiplicador";
    String classPath = ".;./target/classes";
        
    new File("files").mkdirs();
        
    List<String> command = new ArrayList<>();
    command.add("java");
    command.add("-cp");
    command.add(classPath);
    command.add(clase);
    command.addAll(params);
        
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.redirectError(new File("files" + File.separator + "error-" + System.currentTimeMillis() + ".log"));
    pb.redirectOutput(new File("files" + File.separator + outputFile));
        
    Process proceso = pb.start();
    boolean completado = proceso.waitFor(10, TimeUnit.SECONDS);
        
    if (completado && proceso.exitValue() == 0) {
      System.out.println("Proceso completado exitosamente. Archivo de salida: " + outputFile);
      System.out.println("Resultado guardado en output file: ");
      System.out.println(new String(java.nio.file.Files.readAllBytes(new File("files/" + outputFile).toPath())));
    } else {
      System.out.println("Proceso fallido o tiempo de espera agotado. Código de salida: " + proceso.exitValue());
    }
  }
  
  public static void main(String[] args) {
    // Aquí puedes definir los parámetros que quieras pasar al proceso
    List<String> params = new ArrayList<>();
    params.add("5");  // primer número
    params.add("7");  // segundo número
    // Puedes agregar más parámetros si es necesario
        
    String outputFile = "resultado.txt";
        
    try {
      lanzarMultiplicador(params, outputFile);
    } catch (IOException e) {
      System.out.println("Error de E/S al lanzar el proceso: " + e.getMessage());
    } catch (InterruptedException e) {
      System.out.println("El proceso fue interrumpido: " + e.getMessage());
    }
  }
}
