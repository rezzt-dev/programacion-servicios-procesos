package com.jgc.ejercicios.multihilos.servidorBBDD;

import com.jgc.ejercicios.multihilos.servidorBBDD.modelo.Empleado;
import com.jgc.ejercicios.multihilos.servidorBBDD.modelo.ManageEmpleado;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rezzt
 */
public class Cliente {
  public static void main(String[] args) {
    String connection = "localhost";
    int port = 9876;
    
    try (Socket peticion = new Socket(connection, port);
            ObjectInputStream input = new ObjectInputStream(peticion.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(peticion.getOutputStream())) {
      
       // ejemplo | creacion de empleado ->
      Empleado nuevoEmpleado = new Empleado(0, "Juan Perez", 30, "Av.Siempre Viva 123");
      ManageEmpleado operacionCrear = new ManageEmpleado(ManageEmpleado.Metodo.CREAR, nuevoEmpleado);
      output.writeObject(operacionCrear);
      int idEmpleado = (int) input.readObject();
      System.out.println("  -> Empleado creado con el ID: " + idEmpleado);
      
//       // ejemplo | consultar todos los empleados ->
//      ManageEmpleado operacionConsultarAll = new ManageEmpleado(ManageEmpleado.Metodo.CONSULTAR_ALL, null);
//      output.writeObject(operacionConsultarAll);
//      Object respuesta;
//      
//      System.out.println("  -> Lista de empleados: ");
//      while ((respuesta = input.readObject()) != null) {
//        Empleado auxEmple = (Empleado) respuesta;
//        System.out.println("   + Empleado: " + auxEmple.getIdEmple() + " | " + auxEmple.getNombre() + " | " + auxEmple.getEdad() + " | " + auxEmple.getDireccion());
//      }
//      
//       // ejemplo | consultar un empleado concreto ->
//      Empleado consultaEmpleado = new Empleado();
//      consultaEmpleado.setIdEmple(idEmpleado);
//      ManageEmpleado operacionConsultarEmpleado = new ManageEmpleado(ManageEmpleado.Metodo.CONSULTAR_EMPLEADO, consultaEmpleado);
//      output.writeObject(operacionConsultarEmpleado);
//      
//      Empleado auxEmple = (Empleado) input.readObject();
//      if (auxEmple != null) {
//        System.out.println("  -> Empleado: " + auxEmple.getIdEmple() + " | " + auxEmple.getNombre() + " | " + auxEmple.getEdad() + " | " + auxEmple.getDireccion());
//      } else {
//        System.out.println("  -> No se ha encontrado ningun empleado con ese ID.");
//      }
    } catch (IOException | ClassNotFoundException ex) {
      System.err.println(" !Error en la peticion del cliente.");
    }
  }
}
