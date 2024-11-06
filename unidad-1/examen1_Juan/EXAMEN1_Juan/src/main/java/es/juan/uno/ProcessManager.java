/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package es.juan.uno;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * @version 1.0
 * Created on 24 oct 2024
 */
public class ProcessManager {
  /**
   * metodo "lanzarProceso" | lanza un proceso y guarda los resultados en un fichero
   *  > introduces los parametros en un Lista de String
   *  > calcula el minimo y manda el resultado a un fichero "minimo_juan.txt"
   *   - si hay algun error se enviara a un fichero "error_juan.txt"
   *  > ambos ficheros se encuentran dentro de un directorio llamado files (creado internamente).
   */
  
  public static void lanzarProceso (List<String> inputParametros) {
    try {
      new File("files").mkdirs();
      List<String> command = new ArrayList<>();
      String clase = "es.juan.uno.Minimo";
      String classPath = "./target/classes";
      
      command.add("java");
      command.add("-cp");
      command.add(classPath);
      command.add(clase);
      command.addAll(inputParametros);
      
      ProcessBuilder pb = new ProcessBuilder(command);
      pb.redirectOutput(new File("files" + File.separator + "minimo_juan.txt"));
      pb.redirectError(new File("files" + File.separator + "error_juan.txt"));
      
      Process proceso = pb.start();
      boolean completado = proceso.waitFor(10, TimeUnit.SECONDS);
      
      if (completado) {
        System.out.println("El proceso ha finalizado sin erores.");
      } else {
        System.out.println("Ha habido un problema en la ejecucion.");
      }
      
    } catch (InterruptedException ex) {
      System.out.println("El proceso ha fallado debido a: " + ex.getCause());
    } catch (IOException ex) {
      System.out.println("El proceso ha fallado debido a: " + ex.getCause());
    }
  }
  
  /**
   * metodo "main" | metodo principal de la clase
   *  > ejecuta el metodo que queramos (tiene que ser static).
   */
  
  public static void main (String[] args) {
    List<String> params = new ArrayList<>();
    params.add("9");
    params.add("8");
    lanzarProceso(params);
  }
}
