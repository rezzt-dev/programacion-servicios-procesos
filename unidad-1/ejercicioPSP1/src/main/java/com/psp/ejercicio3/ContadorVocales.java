/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.psp.ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * @version 1.0
 * Created on 25 sept 2024
 */
public class ContadorVocales {
  public static void main (String[] args) throws IOException, InterruptedException {
    if (args.length != 1) {
      System.out.println("Uso: java ContadorVocales <fichero_entrada>");
      System.exit(1);
    }
    
    String ficheroEntrada = args[0];
    char[] vocales = {'a', 'e', 'i', 'o', 'u'};
    ExecutorService executor = Executors.newFixedThreadPool(5);
    
    for (char vocal : vocales) {
      executor.execute(() -> {
        try {
          ProcessBuilder pb = new ProcessBuilder("java", "ContarVocal", ficheroEntrada, String.valueOf(vocal));
          pb.inheritIO();
          Process proceso = pb.start();
          proceso.waitFor();
        } catch (IOException | InterruptedException e) {
        }
      });
    }
    
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
    
    int totalVocales = 0;
    for (char vocal : vocales) {
      String nombreResultado = vocal + "_resultado.txt";
      
      try (BufferedReader reader = new BufferedReader(new FileReader(nombreResultado))) {
        totalVocales += Integer.parseInt(reader.readLine());
      } catch (IOException e) {
        System.err.println("ERROR! Al leer el resultado de " + vocal + ": " + e.getMessage());
      }
    }
    
    System.out.println("Total de vocales en el fichero: " + totalVocales);
  }
}
