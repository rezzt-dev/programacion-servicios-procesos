/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.psp.ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * @version 1.0
 * Created on 25 sept 2024
 */
public class ContarVocal {
  public static void main (String[] args) {
    if (args.length != 3) {
      System.out.println("Uso: java ContarVocal <fichero_entrada> <vocal> <fichero_salida>");
      System.exit(1);
    }
    
    String ficheroEntrada = args[0];
    char vocal = args[1].toLowerCase().charAt(0);
    String ficheroSalida = args[2];
    
    int contador = contarVocal(ficheroEntrada, vocal);
    printResultado(ficheroSalida, contador);
  }
  
  private static int contarVocal (String ficheroInput, char vocalInput) {
    int contador = 0;
    
    try (BufferedReader reader = new BufferedReader(new FileReader(ficheroInput))) {
      String linea;
      
      while ((linea = reader.readLine()) != null) {
        contador += linea.toLowerCase().chars().filter(ch -> ch == vocalInput).count();
      }
    } catch (IOException e) {
      System.err.println("ERROR! " + e.getMessage());
    }
    
    return contador;
  }
  
  private static void printResultado (String ficheroOutput, int contadorInput) {
    try (FileWriter writer = new FileWriter(ficheroOutput)) {
      writer.write(String.valueOf(contadorInput));
    } catch (IOException e) {
      System.err.println("ERROR! " + e.getMessage());
    }
  }
}
