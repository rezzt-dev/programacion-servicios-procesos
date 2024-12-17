/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.servidorBBDD;

import com.jgc.ejercicios.multihilos.servidorBBDD.manage.Empleado;
import com.jgc.ejercicios.multihilos.servidorBBDD.manage.EmpleadoJpaController;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rezzt
 */
public class ManageClient extends Thread {
  private static final String DB_URL = "com.jgc_ejercicios-multihilos_jar_1.0-SNAPSHOTPU";
  private static final String DB_USER = "empresapsp";
  private static final String DB_PASSWORD = "empresapsp";

  static EntityManagerFactory emFactory;
  static EntityManager entityManager;
  
  private Socket clientSocket;
  
  public ManageClient (Socket inputSocket) {
    this.clientSocket = inputSocket;
  }
  
  @Override
  public void run () {
    try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
         ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
    ) {
      Operacion simpleOper = (Operacion) ois.readObject();
      
      switch (simpleOper.getMetodo()) {
        case CREAR:
          int idCreado =  (int) crearEmpleado(simpleOper.getEmpleado());
          oos.writeObject(idCreado);
          break;
        case CONSULTAR_ALL:
          List<Empleado> listaEmpleados = consultarListaEmpleados();
          oos.writeObject(listaEmpleados);
          break;
        case CONSULTAR_EMPLEADO:
          Empleado auxEmpleado = consultarEmpleado(simpleOper.getIdEmpleado());
          oos.writeObject(auxEmpleado);
          break;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  private static void iniciarConexion () {
    emFactory = Persistence.createEntityManagerFactory(DB_URL);
    entityManager = emFactory.createEntityManager();
  }
  
  private static void cerrarConexion () {
    entityManager.close();
    emFactory.close();
  }
  
  private long crearEmpleado (Empleado inputEmpleado) throws SQLException {
    EmpleadoJpaController empleController = new EmpleadoJpaController(emFactory);
    empleController.create(inputEmpleado);
    
    return inputEmpleado.getIdEmpleado();
  }
  
  private List<Empleado> consultarListaEmpleados () throws SQLException {
    Query simpleQuery = entityManager.createNamedQuery("Empleado.findALl");
    List<Empleado> listaEmpleados = simpleQuery.getResultList();
    
    return listaEmpleados;
  }
  
  private Empleado consultarEmpleado (int inputIdEmpleado) throws SQLException {
    EmpleadoJpaController empleController = new EmpleadoJpaController(emFactory);
    return empleController.findEmpleado((long) inputIdEmpleado);
  }
}
