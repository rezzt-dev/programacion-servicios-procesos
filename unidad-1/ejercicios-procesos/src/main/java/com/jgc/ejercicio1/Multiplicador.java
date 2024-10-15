/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio1;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 14 oct 2024
 */
public class Multiplicador {
  public static int multiplicar (int num1, int num2) {
    return num1 * num2;
  }
  
  public static void main(String[] args) {
    if (args.length == 2) {
      int num1 = Integer.parseInt(args[0]);
      int num2 = Integer.parseInt(args[1]);
      int result = multiplicar (num1, num2);
      System.out.println("Result: " + result);
    } else {
      System.out.println("Please provide two numbers as arguments.");
    }
  } 
}
