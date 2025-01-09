/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.servidorBBDD;

import com.jgc.ejercicios.multihilos.servidorBBDD.model.Empleado;
import com.jgc.ejercicios.multihilos.servidorBBDD.model.Operacion;
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
    try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
      Empleado nuevoEmpleado = crearEmpleado((long) 1, "Juan",(short) 21, "Calzada");
      Operacion operCrear = new Operacion(Operacion.Metodo.CREAR, nuevoEmpleado);
      oos.writeObject(operCrear);
      long idEmpleadoCreado = (long) ois.readObject();
      System.out.println("  - Empleado creado con el ID: " + idEmpleadoCreado);
      
      Operacion operListarEmples = new Operacion(Operacion.Metodo.CONSULTAR_ALL, null);
      oos.writeObject(operListarEmples);
      List<Empleado> listaEmpleados = (List<Empleado>) ois.readObject();
      
      System.out.println("  - Lista de Empleados: ");
      for (Empleado aux : listaEmpleados) {
        System.out.println("    + Empleado > ID: " + aux.getIdEmpleado() + " | Nombre: " + aux.getNombre() + 
                " | Edad: " + aux.getEdad() + " | Direccion: " + aux.getDireccion());
      }
      
      Operacion operBuscarOper = new Operacion(Operacion.Metodo.CONSULTAR_EMPLEADO, idEmpleadoCreado);
      oos.writeObject(operBuscarOper);
      Empleado empleadoFound = (Empleado) ois.readObject();
      System.out.println("    + Empleado > ID: " + empleadoFound.getIdEmpleado() + " | Nombre: " + empleadoFound.getNombre() + 
              " | Edad: " + empleadoFound.getEdad() + " | Direccion: " + empleadoFound.getDireccion());
      
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  private static Empleado crearEmpleado (long inputId, String inputNombre, short inputEdad, String inputDireccion) {
    Empleado auxEmpleado = new Empleado();
    auxEmpleado.setIdEmpleado(inputId);
    auxEmpleado.setNombre(inputNombre);
    auxEmpleado.setEdad(inputEdad);
    auxEmpleado.setDireccion(inputDireccion);
    
    return auxEmpleado;
  }
}
