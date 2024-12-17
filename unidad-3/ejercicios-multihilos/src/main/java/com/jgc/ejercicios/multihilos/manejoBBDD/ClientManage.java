package com.jgc.ejercicios.multihilos.manejoBBDD;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientManage extends Thread {
  private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:freepdb1";
  private static final String DB_USER = "empresapsp";
  private static final String DB_PASSWORD = "empresapsp";

  private Socket clientSocket;

  public ClientManage (Socket inputSocket) {
    this.clientSocket = inputSocket;
  }
  
  @Override
  public void run () {
    try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
         ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
         Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
      Operacion operacion = (Operacion) ois.readObject();

      switch (operacion.getMetodo()) {
        case CREAR -> {
          int idEmpleadoCreado = crearEmpleado(connection, operacion.getEmpleado());
          oos.writeObject(idEmpleadoCreado);
        }

        case CONSULTAR_ALL -> {
          List<Empleado> listaEmpleados = consultarTodos(connection);
          oos.writeObject(listaEmpleados);
        }

        case CONSULTAR_EMPLEADO -> {
          Empleado empleado = consultarEmpleado(connection, operacion.getIdEmpleado());
          oos.writeObject(empleado);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private int crearEmpleado (Connection connection, Empleado empleado) throws Exception {
    String querySQL = "INSERT INTO EMPLEADO (NOMBRE, EDAD, DIRECCION) VALUES (?, ?, ?)";
    int returnId = 0;

    try (PreparedStatement ps = connection.prepareStatement(querySQL, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, empleado.getNombre());
      ps.setInt(1, empleado.getEdad());
      ps.setString(1, empleado.getDireccion());
      ps.executeUpdate();

      try (ResultSet rs = ps.getResultSet()) {
        if (rs.next()) {
          returnId = rs.getInt(1);
        }
      }
    }
    
    return returnId;
  }

  private List<Empleado> consultarTodos (Connection connection) throws Exception {
    List<Empleado> listaEmpleados = new ArrayList<>();

    String querySQL = "SELECT * FROM EMPLEADO";
    try (PreparedStatement ps = connection.prepareStatement(querySQL);
         ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        listaEmpleados.add(new Empleado(
          rs.getLong("ID_EMPLEADO"),
          rs.getString("NOMBRE"),
          rs.getShort("EDAD"),
          rs.getString("DIRECCION")
        ));
      }
    }

    return listaEmpleados;
  }

  private Empleado consultarEmpleado (Connection connection, int idEmpleado) throws Exception {
    String querySQL = "SELECT * FROM EMPLEADO WHERE ID_EMPLEADO = ?";
    Empleado auxEmpleado = new Empleado();
    
    try (PreparedStatement ps = connection.prepareStatement(querySQL)) {
      ps.setInt(1, idEmpleado);

      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          auxEmpleado = new Empleado(
            rs.getLong("ID_EMPLEADO"),
            rs.getString("NOMBRE"),
            rs.getShort("EDAD"),
            rs.getString("DIRECCION")
          );
        }
      }
    }

    return auxEmpleado;
  }
}
