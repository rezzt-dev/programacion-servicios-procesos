/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package es.juan.uno;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * @version 1.0
 * Created on 24 oct 2024
 */
public class Minimo {
  /**
   * metodo "calcularMinimo" | calcula el numero minimo entre 2 numeros
   *  > introduces 2 numeros por parametros, las posibles respuestas son:
   * @param num1 
   * @param num2
   *  responde con el resultado de la operacion.
   */
  
  private static void calcularMinimo (int num1, int num2) {
    int resultado = 0;
    
    if (num1 == num2) {
      System.out.println("El numero " + num1 + " es igual a " + num2);
    } else {
      if (num1 < num2) {
        System.out.println("El numero " + num1 + " es menor que " + num2);
      } else {
        System.out.println("El numero " + num2 + " es menor que " + num1);
      }
    }
  }
  
  public static void main (String[] args) {
    if (args.length == 2) {
      int num1 = Integer.parseInt(args[0]);
      int num2 = Integer.parseInt(args[1]);
      
      calcularMinimo(num1, num2);
    }
  }
}
