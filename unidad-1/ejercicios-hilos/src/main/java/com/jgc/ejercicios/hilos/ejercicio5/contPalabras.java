/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 13 oct 2024
 */
public class contPalabras {
  private static class contLinea implements Callable<Integer> {
    private String linea;
    
    public contLinea (String inputLinea) {
      linea = inputLinea;
    }
    
    @Override
    public Integer call() {
      String[] words = linea.trim().split("\s+");
      return words.length;
    }
  }
  
  public static void main (String[] args) {
    String filename = "input.txt";
    List<String> lineas = new ArrayList<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String linea;
      while ((linea = reader.readLine()) != null) {
        lineas.add(linea);
      }
    } catch (IOException e) {
      System.err.println("Error al leer el archivo: " + e.getMessage());
      return;
    }
    
    ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    List<Future<Integer>> futures = new ArrayList<>();

    for (String line : lineas) {
      futures.add(executor.submit(new contLinea(line)));
    }

    int totalWords = 0;
    for (Future<Integer> future : futures) {
      try {
        totalWords += future.get();
      } catch (InterruptedException | ExecutionException e) {
        System.err.println("Error al obtener el resultado: " + e.getMessage());
      }
    }

    executor.shutdown();
    System.out.println("El número total de palabras es: " + totalWords);
  }
}
