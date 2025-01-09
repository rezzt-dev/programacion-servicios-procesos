/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.servidorBBDD;

import com.jgc.ejercicios.multihilos.servidorBBDD.model.Empleado;
import com.jgc.ejercicios.multihilos.servidorBBDD.model.EmpleadoJpaController;
import com.jgc.ejercicios.multihilos.servidorBBDD.model.Operacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rezzt
 */
public class ManejoClientes extends Thread {
  static EntityManagerFactory emFactory;
  static EntityManager entityManager;
  
  private Socket clientSocket;
  
  public ManejoClientes (Socket inputSocket) {
    this.clientSocket = inputSocket;
  }
  
  @Override
  public void run () {
    try (ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
         ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
      Operacion oper = (Operacion) ois.readObject();
      
      abrirConexion();
      switch (oper.getMetodo()) {
        case CREAR -> {
          long idEmpleadoCreado = crearEmpleado(oper.getEmpleado());
          System.out.println("ID del empleado creado: " + idEmpleadoCreado);
          oos.writeObject(idEmpleadoCreado);
          oos.flush();
        }
        
        case CONSULTAR_ALL -> {
          List<Empleado> listaEmpleados = listarEmpleados();
          oos.writeObject(listaEmpleados);
          oos.flush();
        }
        
        case CONSULTAR_EMPLEADO -> {
          Empleado empleadoFound = encontrarEmpleado(oper.getIdEmpleado());
          oos.writeObject(empleadoFound);
          oos.flush();
        }
      }
      cerrarConexion();
      
    } catch (Exception ex) {
      Logger.getLogger(ManejoClientes.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
 //—————————————————————————————————————————————————————————————————————————————————————————————————————
  private long crearEmpleado (Empleado inputEmpleado) {
    EmpleadoJpaController empleController = new EmpleadoJpaController(emFactory);
    empleController.create(inputEmpleado);
    return inputEmpleado.getIdEmpleado();
  }
  
  private List<Empleado> listarEmpleados () {
    EmpleadoJpaController empleController = new EmpleadoJpaController(emFactory);
    List<Empleado> listaEmpleados = new ArrayList<>();
    
    listaEmpleados = empleController.findEmpleadoEntities();
    return listaEmpleados;
  }
  
  private Empleado encontrarEmpleado (long inputIdEmpleado) {
    EmpleadoJpaController empleController = new EmpleadoJpaController(emFactory);
    Empleado auxEmpleado = new Empleado();
    
    auxEmpleado = empleController.findEmpleado(inputIdEmpleado);
    return auxEmpleado;
  }
  
 //—————————————————————————————————————————————————————————————————————————————————————————————————————
  private static void abrirConexion () {
    emFactory = Persistence.createEntityManagerFactory("com.jgc_ejercicios-multihilos_jar_1.0-SNAPSHOTPU");
    entityManager = emFactory.createEntityManager();
  }
  private static void cerrarConexion () {
    emFactory.close();
    entityManager.close();
  }
}
