/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio6;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 13 oct 2024
 */
public class procesoPrincipal {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Uso: java PrincipalProcess <directorio> <palabra>");
      return;
    }

    String directorio = args[0];
    String palabraBuscada = args[1];

    try {
    List<Path> archivos = Files.list(Paths.get(directorio)).filter(path -> path.toString().endsWith(".txt")).collect(Collectors.toList());

    ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    List<Future<infoArchivo>> futures = new ArrayList<>();

    for (Path archivo : archivos) {
      Callable<infoArchivo> tarea = new palabrasArchivo(archivo.toString(), palabraBuscada);
      futures.add(executor.submit(tarea));
    }

    List<infoArchivo> resultados = new ArrayList<>();
    for (Future<infoArchivo> future : futures) {
      resultados.add(future.get());
    }

    executor.shutdown();
    escribirResultados(resultados);

    } catch (IOException | InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  private static void escribirResultados(List<infoArchivo> resultados) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultado_busqueda.txt"))) {
      for (infoArchivo datos : resultados) {
        writer.write(datos.getOcurrencias() + " ocurrencias en el fichero " + datos.getNombreArchivo() + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
