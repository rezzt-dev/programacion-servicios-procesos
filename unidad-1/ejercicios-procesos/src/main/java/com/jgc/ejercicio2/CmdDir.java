/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 14 oct 2024
 */
public class CmdDir {
  public static void main (String[] args) {
    try {
      ProcessBuilder cmdDir = new ProcessBuilder("cmd", "/C", "dir");
      Process proceso = cmdDir.start();
      
      BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
      String linea;
      
      while ((linea = reader.readLine()) != null) {
        System.out.println(linea);
      }
      
      int exitCode = proceso.waitFor();
      
      if (exitCode == 0) {
        System.out.println("proceso finalizado.");
      } else {
        System.out.println("error: " + exitCode);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
