/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.servidorBBDD;

import com.jgc.ejercicios.multihilos.servidorBBDD.manage.Empleado;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author rezzt
 */
public class Cliente {
  private static final String SERVER_HOST = "localhost";
  private static final int SERVER_PORT = 9876;
  
  public static void main(String[] args) {
    try (Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
         ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
         ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())
    ) {
      Empleado newEmpleado = createEmpleado(11, "Juan", 21, "Calzada");
      Operacion operacionCrear = new Operacion(Operacion.Metodo.CREAR, newEmpleado);
      oos.writeObject(operacionCrear);
      int idEmpleCreado = (int) ois.readObject();
      System.out.println("  - Empleado creado con el ID: " + idEmpleCreado);
      
      Operacion operacionConsultarEmpleados = new Operacion(Operacion.Metodo.CONSULTAR_ALL, null);
      oos.writeObject(operacionConsultarEmpleados);
      List<Empleado> listaEmpleados = (List<Empleado>) ois.readObject();
      listaEmpleados.forEach(System.out::println);
      
      Operacion operacionConsultarEmpleado = new Operacion(Operacion.Metodo.CONSULTAR_EMPLEADO, idEmpleCreado);
      oos.writeObject(operacionConsultarEmpleado);
      Empleado auxEmple = (Empleado) ois.readObject();
      System.out.println("  - Empleado consultado: " + auxEmple.getIdEmpleado() + " | Nombre: " + auxEmple.getNombre() + 
              " | Edad: " + auxEmple.getEdad() + " | Direccion: " + auxEmple.getDireccion());
      
    } catch (IOException | ClassNotFoundException ex) {
      ex.printStackTrace();
    }
  }
  
  private static Empleado createEmpleado (int inputId, String inputNombre, int inputEdad, String inputDireccion) {
    Empleado auxEmple = new Empleado();
    auxEmple.setIdEmpleado((long) inputId);
    auxEmple.setNombre(inputNombre);
    auxEmple.setEdad((short) inputEdad);
    auxEmple.setDireccion(inputDireccion);
    
    return auxEmple;
  }
}
