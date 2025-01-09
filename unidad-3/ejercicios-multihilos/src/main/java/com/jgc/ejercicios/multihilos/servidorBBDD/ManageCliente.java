/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejercicios.multihilos.servidorBBDD;

import com.jgc.ejercicios.multihilos.servidorBBDD.modelo.Empleado;
import com.jgc.ejercicios.multihilos.servidorBBDD.modelo.ManageEmpleado;
import java.sql.Connection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rezzt
 */
public class ManageCliente extends Thread {
  private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
  private static final String DB_PARAMS = "pspEjercicio3";
  
  private Socket peticionCliente;
  
  public ManageCliente (Socket inputPeticionCliente) {
    this.peticionCliente = inputPeticionCliente;
  }
  
  @Override
  public void run () {
    try (ObjectInputStream input = new ObjectInputStream(peticionCliente.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(peticionCliente.getOutputStream());
            Connection connection = DriverManager.getConnection(DB_URL, DB_PARAMS, DB_PARAMS)) {
      ManageEmpleado operador = (ManageEmpleado) input.readObject();
      switch (operador.getMetodo()) {
        case CREAR -> {
          Empleado auxEmple = operador.getEmpleado();
          String insertQuery = "INSERT INTO EMPLEADO (NOMBRE, EDAD, DIRECCION) VALUES (?,?,?)";
          
          try (PreparedStatement stmt = connection.prepareStatement(insertQuery, new String[]{"ID_EMPLEADO"})) {
            stmt.setString(1, auxEmple.getNombre());
            stmt.setInt(2, auxEmple.getEdad());
            stmt.setString(3, auxEmple.getDireccion());
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
              int idReturnEmple = generatedKeys.getInt(1);
              output.writeObject(idReturnEmple);
            }
          }
        }
        
        case CONSULTAR_ALL -> {
          String selectAllQuery = "SELECT * FROM EMPLEADO";
          
          try (Statement stmt = connection.createStatement();
                  ResultSet rs = stmt.executeQuery(selectAllQuery)) {
            while (rs.next()) {
              Empleado auxEmple = new Empleado (
                      rs.getInt("ID_EMPLEADO"),
                      rs.getString("NOMBRE"),
                      rs.getInt("EDAD"),
                      rs.getString("DIRECCION")
              );
              output.writeObject(auxEmple);
            }
          }
        }
        
        case CONSULTAR_EMPLEADO -> {
          int id = operador.getEmpleado().getIdEmple();
          String selectEmpleQuery = "SELECT * FROM EMPLEADO WHERE ID_EMPLEADO = ?";
          
          try (PreparedStatement stmt = connection.prepareStatement(selectEmpleQuery)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
              Empleado auxEmple = new Empleado (
                      rs.getInt("ID_EMPLEADO"),
                      rs.getString("NOMBRE"),
                      rs.getInt("EDAD"),
                      rs.getString("DIRECCION")
              );
              output.writeObject(auxEmple);
            } else {
              output.writeObject(null);
            }
          }
        }
      }
    } catch (IOException | ClassNotFoundException | SQLException ex) {
      System.err.println(" -> Se ha producido un error inesperado por parte del hilo.");
    }
  }
}
