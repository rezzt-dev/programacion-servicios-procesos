/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 14 oct 2024
 */
public class ContarVocal {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Uso: java ContarVocal <fichero_entrada> <vocal> <fichero_salida>");
      System.exit(1);
    }
        
    String ficheroEntrada = args[0];
     char vocal = args[1].toLowerCase().charAt(0);
    String ficheroSalida = args[2];
        
    int contador = contarVocal(ficheroEntrada, vocal);
    printResultado(ficheroSalida, contador);
        
    // Imprimir el resultado para depuración
    System.out.println("Número de veces que aparece '" + vocal + "': " + contador);
  }
    
  private static int contarVocal(String inputFicheroEntrada, char inputVocal) {
    int contador = 0;
        
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFicheroEntrada))) {
      String line;
            
      while ((line = reader.readLine()) != null) {
        contador += line.toLowerCase().chars().filter(ch -> ch == inputVocal).count();
      }
    } catch (IOException e) {
      System.err.println("ERROR al leer el archivo: " + e.getMessage());
    }
        
    return contador;
  }
    
  private static void printResultado(String inputFicheroSalida, int inputContador) {
    try (FileWriter writer = new FileWriter(inputFicheroSalida)) {
      writer.write(String.valueOf(inputContador));
    } catch (IOException e) {
      System.err.println("ERROR al escribir el resultado: " + e.getMessage());
    }
  }
}
